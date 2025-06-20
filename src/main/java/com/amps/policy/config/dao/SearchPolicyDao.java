package com.amps.policy.config.dao;

import com.amps.policy.config.dto.PolicySearchDTO;
import com.amps.policy.config.dto.PolicySearchResultDTO;

import java.util.List;

public interface SearchPolicyDao {
	public List<PolicySearchResultDTO> serachCriteria(PolicySearchDTO policyDto);
}
