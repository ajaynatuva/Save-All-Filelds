package com.amps.policy.config.service;

import java.util.List;

import com.amps.policy.config.model.ConditionCodeActionLkp;
import com.amps.policy.config.model.PolicyConditionCode;

public interface PolicyConditionCodeService {
	
	List<ConditionCodeActionLkp> getConditionLkpData();
	
	List<PolicyConditionCode> getPolicyConditionDataById(int id);
	
	void postConditionTypeData(List<PolicyConditionCode> policyConditionTypes);
	
	void deletePolicyConditionTypeData(int key);

}
