package com.amps.policy.config.service.impl;

import com.amps.policy.config.dao.ClientAssignmentDao;
import com.amps.policy.config.dao.ClientGroupExclusionsDao;
import com.amps.policy.config.dao.ClientGroupProductDao;
import com.amps.policy.config.dto.*;
import com.amps.policy.config.service.ClientDataMigrationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientDataMigrationServiceImpl implements ClientDataMigrationService {

    @Autowired
    ClientGroupProductDao  clientGroupProductDao;

    @Autowired
    ClientGroupExclusionsDao clientGroupExclusionsDao;

    @Autowired
    ClientAssignmentDao clientAssignmentDao;

    @Override
    public void clientMigration() {
        Map<Integer,HashMap<Integer,List<ClientAssignmentDTO>>> clientAssignmentMap = mapClientAssignmentData();

        List<ClientAssignmentDTO> clientGroupProductList = clientGroupProductDao.getClientGroupProductData();
        Map<Integer, List<ClientPolicyExclusionsDTO>> policyClientExclusionMap = mapPolicyExclusions(clientGroupExclusionsDao.getPolicyExclusionData());
        List<ClientAssignmentDTO> allClientAssignments = getClientAssignmentDTOS(clientAssignmentMap, clientGroupProductList, policyClientExclusionMap);
        int batchSize = 1000;
        saveClientAssignmentsInBatches(allClientAssignments, batchSize);
    }

    private List<ClientAssignmentDTO> getClientAssignmentDTOS(Map<Integer, HashMap<Integer, List<ClientAssignmentDTO>>> clientAssignmentMap, List<ClientAssignmentDTO> clientGroupProductList, Map<Integer, List<ClientPolicyExclusionsDTO>> policyClientExclusionMap) {
        List<ClientPolicyDTO> policyList =  clientGroupExclusionsDao.getPolicyData();
        List<ClientAssignmentDTO> allClientAssignments = new ArrayList<>();
        policyList.forEach(policy -> {
            List<ClientAssignmentDTO> clientAssignments =processPolicy(clientAssignmentMap.getOrDefault(policy.getPolicyId(), null)
                    , policy, clientGroupProductList, policyClientExclusionMap.getOrDefault(policy.getPolicyId(), null));
            allClientAssignments.addAll(clientAssignments);
        });
        return allClientAssignments;
    }

    private List<ClientAssignmentDTO> processPolicy(HashMap<Integer,List<ClientAssignmentDTO>> clientAssignmentMap,ClientPolicyDTO policy,
                               List<ClientAssignmentDTO> clientGroupProductList, List<ClientPolicyExclusionsDTO> policyClientExclusionList) {
        List<ClientAssignmentDTO> clientGroupList=checkClientIsPresent(clientAssignmentMap,clientGroupProductList);
        List<ClientAssignmentDTO> filteredClientGroupList = CollectionUtils.isEmpty(policyClientExclusionList)
                ? clientGroupList
                : getFilteredClientGroupList(clientGroupList, policyClientExclusionList);

        return mapClientGroupProductDTO(policy, filteredClientGroupList);
    }

    private List<ClientAssignmentDTO> getFilteredClientGroupList(List<ClientAssignmentDTO> clientGroupProductList, List<ClientPolicyExclusionsDTO> filteredPolicyExclusionList) {
        return clientGroupProductList.stream()
                .filter(clientGroupProduct -> filteredPolicyExclusionList.stream()
                        .noneMatch(policyExclusion -> policyExclusion.getClientGroupId().equals(clientGroupProduct.getClientGroupId())))
                .collect(Collectors.toList());
    }

    private List<ClientAssignmentDTO> mapClientGroupProductDTO(ClientPolicyDTO policy,List<ClientAssignmentDTO> clientGroupProductList){
        if(Objects.isNull(clientGroupProductList))
            return new ArrayList<>();
        List<ClientAssignmentDTO> clientAssignmentDTOList=new ArrayList<>();
        clientGroupProductList.forEach(clientGroupProductDTO -> {
            ClientAssignmentDTO clientAssignmentDTO=new ClientAssignmentDTO();
            BeanUtils.copyProperties(clientGroupProductDTO,clientAssignmentDTO);
            clientAssignmentDTO.setPolicyId(policy.getPolicyId());
            clientAssignmentDTO.setExcludeClientSpecificCodes(true);
            clientAssignmentDTOList.add(clientAssignmentDTO);
        });
        return clientAssignmentDTOList;
    }

    private Map<Integer,HashMap<Integer,List<ClientAssignmentDTO>>> mapClientAssignmentData(){
        List<ClientAssignmentDTO> clientAssignmentDTOList=clientGroupProductDao.getClientAssignmentData();
        Map<Integer,HashMap<Integer,List<ClientAssignmentDTO>>> clientAssignmentMap=new HashMap<>();
        clientAssignmentDTOList.forEach(clientAssignmentDTO -> {
            HashMap<Integer,List<ClientAssignmentDTO>> clientAssignment = addToClientAssignmentToMap(clientAssignmentMap, clientAssignmentDTO);
            clientAssignmentMap.put(clientAssignmentDTO.getPolicyId(),clientAssignment);
        });
        return clientAssignmentMap;
    }

    private HashMap<Integer,List<ClientAssignmentDTO>> addToClientAssignmentToMap(
            Map<Integer,HashMap<Integer,List<ClientAssignmentDTO>>> clientAssignmentMap,ClientAssignmentDTO clientAssignmentDTO){
        HashMap<Integer,List<ClientAssignmentDTO>> clientAssignment=clientAssignmentMap.getOrDefault(clientAssignmentDTO.getPolicyId(),new HashMap<>());
        List<ClientAssignmentDTO> clientAssignmentDTOList=clientAssignment.getOrDefault(clientAssignmentDTO.getClientGroupId(),new ArrayList<>());
        clientAssignmentDTOList.add(clientAssignmentDTO);
        clientAssignment.put(clientAssignmentDTO.getClientGroupId(),clientAssignmentDTOList);
        return clientAssignment;
    }

    private List<ClientAssignmentDTO> checkClientIsPresent(HashMap<Integer,List<ClientAssignmentDTO>> clientAssignmentMap,List<ClientAssignmentDTO> clientGroupProductList){
        return CollectionUtils.isEmpty(clientAssignmentMap) ? clientGroupProductList
                :clientGroupProductList.stream().filter(clientGroupProductDTO -> !clientAssignmentMap.containsKey(clientGroupProductDTO.getClientGroupId())).toList();
    }

    private Map<Integer, List<ClientPolicyExclusionsDTO>> mapPolicyExclusions(List<ClientPolicyExclusionsDTO> clientPolicyExclusionsList){
        return clientPolicyExclusionsList.stream().collect(Collectors.groupingBy(ClientPolicyExclusionsDTO::getPolicyId));
    }

    private void saveClientAssignmentsInBatches(List<ClientAssignmentDTO> allClientAssignments, int batchSize) {
        int totalSize = allClientAssignments.size();
        for (int i = 0; i < totalSize; i += batchSize) {
            int end = Math.min(totalSize, i + batchSize);
            List<ClientAssignmentDTO> batch = allClientAssignments.subList(i, end);
            clientAssignmentDao.postClientAssignmentData1(batch);
        }
    }
}
