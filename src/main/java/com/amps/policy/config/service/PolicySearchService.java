package com.amps.policy.config.service;

import com.amps.policy.config.dto.PolicySearchDTO;
import com.amps.policy.config.dto.PolicySearchResultDTO;

import java.util.List;

public interface PolicySearchService {

    public List<PolicySearchResultDTO> serachCriteria(PolicySearchDTO policyDto);

}
