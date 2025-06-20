package com.amps.policy.config.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amps.policy.config.dao.PolicyViewDao;
import com.amps.policy.config.dao.PolicyViewExportedDao;
import com.amps.policy.config.dto.CategoryDTO;
import com.amps.policy.config.dto.PolicyViewDTO;
import com.amps.policy.config.dto.PolicyViewExportDTO;
import com.amps.policy.config.service.PolicyViewService;

@Component
public class PolicyViewServiceImpl implements PolicyViewService {

	@Autowired
	PolicyViewDao policyViewDao;

	@Autowired
	PolicyViewExportedDao policyViewExportedDao;

	@Override
	public List<CategoryDTO> getCategoryData() {
		// TODO Auto-generated method stub
		return policyViewDao.getCategoryData();
	}

	@Override
	public List<PolicyViewDTO> getSubPolicyByCat(List<PolicyViewDTO> policyViewDTO) {
		// TODO Auto-generated method stub
		return policyViewDao.getSubPolicyByCat(policyViewDTO);
	}

	@Override
	public List<PolicyViewDTO> getFilterdReasonPolicies(List<PolicyViewDTO> policyViewDTO) {
		// TODO Auto-generated method stub
		return policyViewDao.getReasonPolicies(policyViewDTO);
	}

	@Override
	public List<PolicyViewDTO> getFilterdPolicies(List<PolicyViewDTO> policyViewDTO) {
		// TODO Auto-generated method stub
		return policyViewDao.getTotalPolicies(policyViewDTO);
	}

	@Override
	public List<PolicyViewDTO> getPolicyViewData(List<PolicyViewDTO> policyViewDTO) {
		// TODO Auto-generated method stub
		return policyViewDao.searchCriteria(policyViewDTO);
	}

	@Override
	public byte[] getExportPolicies(List<PolicyViewExportDTO> paramsMap, String type) {
		// TODO Auto-generated method stub
		return policyViewExportedDao.getExportPolicies(paramsMap, type);
	}

}
