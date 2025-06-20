package com.amps.policy.config.service.impl;

import com.amps.policy.config.dao.NpiDao;
import com.amps.policy.config.dto.ChangesDTO;
import com.amps.policy.config.model.Changes;
import com.amps.policy.config.model.Npi;
import com.amps.policy.config.model.Policy;
import com.amps.policy.config.model.TaxId;
import com.amps.policy.config.repository.NpiRepository;
import com.amps.policy.config.service.NpiService;
import com.amps.policy.config.service.PolicyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NpiServiceImpl implements NpiService {
    @Autowired
    NpiRepository npiRepository;

    @Autowired
    NpiDao npiDao;

    @Autowired
    PolicyService policyService;

    Logger logger = LogManager.getLogger(NpiServiceImpl.class);


    @Override
    public void saveNpiData(List<Npi> npiList) {
        npiDao.saveNpiDataToSource(npiList);
    }

    @Override
    public void deleteByNpi(List<Npi> npiList) {
        npiDao.deleteClientNpiExclusionsByPolicyId(npiList);
    }

    @Override
    public List<Npi> getNpiGroupedDetails(Integer policyId) {
        return npiDao.getNpiGroupedDetails(policyId);
    }

    @Override
    public void saveNewNpiChanges(ChangesDTO changesDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Changes changes = modelMapper.map(changesDTO, Changes.class);
        Policy policy = policyService.findByPolicyId(changesDTO.getPolicyId());
        changes.setPolicyId(policy);
        changes.setUpdatedOn(changes.getUpdatedOn() == null ? new Date() : changes.getUpdatedOn());
        policyService.saveChanges(changes);
    }

    @Override
    public List<Npi> loadDataToTarget(Integer policyId) {
    	 List<Npi> result = new ArrayList<>();
         List<Npi> deleteActionNPIData;
         try {
             logger.info(" Loading stage NPI data to Client NPI Exclusions for policy id " + policyId);
             List<Npi> NpiDTO = getNPIPolicyIdData(policyId);
             if (!NpiDTO.isEmpty()) {
            	 deleteActionNPIData = npiDao.getAllDeleteActionNPIData(policyId);
            	 npiDao.deleteClientNpiExclusionsByPolicyId(deleteActionNPIData);
             }
             npiDao.saveAllStageDataToTarget(policyId);
             logger.info(" Loaded data to Client NPI Exclusions for policy id " + policyId);
             result = getNpiGroupedDetails(policyId);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return result;    }

	@Override
	public List<Npi> getNPIPolicyIdData(int policyId) {
		// TODO Auto-generated method stub
		return npiRepository.findByPolicyId(policyId);
	}
}
