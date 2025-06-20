package com.amps.policy.config.service.impl;

import com.amps.policy.config.dao.SearchPolicyDao;
import com.amps.policy.config.dto.PolicySearchDTO;
import com.amps.policy.config.dto.PolicySearchResultDTO;
import com.amps.policy.config.service.PolicySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicySearchServiceImpl implements PolicySearchService {

    @Autowired
    SearchPolicyDao searchPolicyDao;

    @Override
    public List<PolicySearchResultDTO> serachCriteria(PolicySearchDTO policyDto) {
        return searchPolicyDao.serachCriteria(policyDto);
    }
}
