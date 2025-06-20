package com.amps.policy.config.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amps.policy.config.dao.ClientAssignmentDao;
import com.amps.policy.config.dto.ClientAssignmentDTO;
import com.amps.policy.config.dto.ClientAssignmentDTO1;
import com.amps.policy.config.dto.copyClientDTO;
import com.amps.policy.config.model.ClientAssignment;
import com.amps.policy.config.repository.NewClientSetupRepository;
import com.amps.policy.config.service.ClientAssignmentService;
@Service
public class ClientAssignmentServiceImpl implements ClientAssignmentService {

    @Autowired
    ClientAssignmentDao clientAssignmentDao;

    @Autowired
    NewClientSetupRepository newClientSetupRepository;

    public void saveClientAssignment(List<ClientAssignmentDTO> clientAssignment) {
        clientAssignmentDao.postClientAssignmentData1(clientAssignment);
    }

    public void saveClientAssignment1(List<ClientAssignmentDTO1> clientAssignment) {
        List<ClientAssignmentDTO> data = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for(ClientAssignmentDTO1 client :  clientAssignment) {
            ClientAssignmentDTO mappedClass = mapper.map(client, ClientAssignmentDTO.class);
            data.add(mappedClass);
        }
        clientAssignmentDao.postClientAssignmentData1(data);
    }

    public void updateClientAssignment(ClientAssignment clientAssignment) {
        clientAssignmentDao.updateClientAssignmentData(clientAssignment);
    }

    public void updateClientAssignment1(ClientAssignmentDTO1 clientAssignmentDTO1) {
        ModelMapper mapper = new ModelMapper();
        ClientAssignmentDTO mappedClass = mapper.map(clientAssignmentDTO1, ClientAssignmentDTO.class);
        clientAssignmentDao.updateClientAssignmentData1(mappedClass);
    }

    public void deleteClientAssignmentData(int id) {
        clientAssignmentDao.deleteClientAssignmentData(id);
    }

    public List<ClientAssignmentDTO1> getClientAssignmentTabData(int id) {
        return clientAssignmentDao.getClientAssignmentData(id);
    }

	
    @Override
	public List<copyClientDTO> getCopyClientAssignmentData() {
		// TODO Auto-generated method stub
		return clientAssignmentDao.getCopyClientAssignmentData();
	}
    
    @Override
	public List<copyClientDTO> getClientAssignmentPolicyIds(List<copyClientDTO> clientGroupIds,
			List<copyClientDTO> clientGroupIds1) throws ParseException {
		// TODO Auto-generated method stub
		return clientAssignmentDao.getPolicyIds(clientGroupIds, clientGroupIds1);
	}

    @Override
    public List<ClientAssignmentDTO> getActiveClientGroups() {
        return clientAssignmentDao.getActiveClientGroups();
    }

    @Override
    public List<ClientAssignmentDTO> getActiveClientGroupsNotHp() {
       return clientAssignmentDao.getActiveClientGroupsNotHp();
    }
}
