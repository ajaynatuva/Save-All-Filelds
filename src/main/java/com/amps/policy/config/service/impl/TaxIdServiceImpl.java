package com.amps.policy.config.service.impl;

import com.amps.policy.config.dao.TaxIdDao;
import com.amps.policy.config.dto.ChangesDTO;
import com.amps.policy.config.model.Changes;
import com.amps.policy.config.model.Policy;
import com.amps.policy.config.model.TaxId;
import com.amps.policy.config.repository.TaxIdRepository;
import com.amps.policy.config.service.PolicyService;
import com.amps.policy.config.service.TaxIdService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaxIdServiceImpl implements TaxIdService {
    @Autowired
    TaxIdRepository taxIdRepository;

    @Autowired
    TaxIdDao taxIdDao;

    @Autowired
    PolicyService policyService;

    Logger logger = LogManager.getLogger(TaxIdServiceImpl.class);

    @Override
    public List<TaxId> getTaxIdOfPolicy(int policyId) {
        return taxIdRepository.findByPolicyId(policyId);
    }

    @Override
    public void saveTaxIdData(List<TaxId> taxIdList) {
        taxIdDao.saveTaxIdDataToSource(taxIdList);
    }

    @Override
    public void deleteByTaxId(List<TaxId> taxIdList) {
        taxIdDao.deleteClientTinExclusionsByPolicyId(taxIdList);
    }

    @Override
    public void uploadTaxId(List<TaxId> TaxId) {

    }

    public List<TaxId> loadDataToTarget(Integer policyId) {
        List<TaxId> result = new ArrayList<>();
        List<TaxId> deleteActionCTEData;
        try {
            logger.info(" Loading stage TaxId data to Client TIN Exclusions for policy id " + policyId);
            List<TaxId> taxIdDTO = getTaxIdOfPolicy(policyId);
            if (!taxIdDTO.isEmpty()) {
                deleteActionCTEData = taxIdDao.getAllDeleteActionCTEData(policyId);
                taxIdDao.deleteClientTinExclusionsByPolicyId(deleteActionCTEData);
            }
            taxIdDao.saveAllStageDataToTarget(policyId);
            logger.info(" Loaded data to Client TIN Exclusions for policy id " + policyId);
            result = getTaxIdGroupedDetails(policyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<TaxId> getTaxIdGroupedDetails(Integer policyId) {
        return taxIdDao.getTaxIdGroupedDetails(policyId);
    }

    @Override
    public void saveNewTaxIDChanges(ChangesDTO changesDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Changes changes = modelMapper.map(changesDTO, Changes.class);
        Policy policy = policyService.findByPolicyId(changesDTO.getPolicyId());
        changes.setPolicyId(policy);
        changes.setUpdatedOn(changes.getUpdatedOn() == null ? new Date() : changes.getUpdatedOn());
        policyService.saveChanges(changes);
    }
}
