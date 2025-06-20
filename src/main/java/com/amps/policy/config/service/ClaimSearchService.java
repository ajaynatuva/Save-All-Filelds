package com.amps.policy.config.service;

import com.amps.policy.config.dto.ClaimSearchDTO;


public interface ClaimSearchService{
	
	public String claimConditionQuery(ClaimSearchDTO claimSearchDto);
	
}
