package com.amps.policy.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amps.policy.config.dao.PolicyConditionCodeDao;
import com.amps.policy.config.model.ConditionCodeActionLkp;
import com.amps.policy.config.model.PolicyConditionCode;
import com.amps.policy.config.service.PolicyConditionCodeService;

@Component
public class PolicyConditionCodeServiceImpl implements PolicyConditionCodeService {

	@Autowired
	PolicyConditionCodeDao policyConditionDao;

	@Override
	public List<ConditionCodeActionLkp> getConditionLkpData() {
		// TODO Auto-generated method stub
		return policyConditionDao.getConditionData();
	}

	@Override
	public List<PolicyConditionCode> getPolicyConditionDataById(int id) {
		// TODO Auto-generated method stub
		return policyConditionDao.getPolicyConditionData(id);
	}

	@Override
	public void postConditionTypeData(List<PolicyConditionCode> policyConditionTypes) {
		// TODO Auto-generated method stub
		policyConditionDao.PostConditionData(policyConditionTypes);
	}

	@Override
	public void deletePolicyConditionTypeData(int key) {
		// TODO Auto-generated method stub
		policyConditionDao.DeletePolicyConditionData(key);
	}
}
