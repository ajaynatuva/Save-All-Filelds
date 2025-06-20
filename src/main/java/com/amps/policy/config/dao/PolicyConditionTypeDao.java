package com.amps.policy.config.dao;

import com.amps.policy.config.model.ConditionCodeActionLkp;
import com.amps.policy.config.model.PolicyConditionCode;

import java.util.List;

public interface PolicyConditionTypeDao {

	public List<ConditionCodeActionLkp> getConditionData();
	
	public List<PolicyConditionCode> getPolicyConditionData(Integer Id);
	
	public void PostConditionData(List<PolicyConditionCode>policyConditionTypes);
	
	public void DeleteData(Integer key);	
}
