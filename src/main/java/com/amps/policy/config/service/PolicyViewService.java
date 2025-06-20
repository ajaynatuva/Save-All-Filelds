package com.amps.policy.config.service;

import java.util.List;

import com.amps.policy.config.dto.CategoryDTO;
import com.amps.policy.config.dto.PolicyViewDTO;
import com.amps.policy.config.dto.PolicyViewExportDTO;

public interface PolicyViewService {

	List<CategoryDTO> getCategoryData();

	List<PolicyViewDTO> getSubPolicyByCat(List<PolicyViewDTO> policyViewDTO);

	List<PolicyViewDTO> getFilterdReasonPolicies(List<PolicyViewDTO> policyViewDTO);

	List<PolicyViewDTO> getFilterdPolicies(List<PolicyViewDTO> policyViewDTO);

	List<PolicyViewDTO> getPolicyViewData(List<PolicyViewDTO> policyViewDTO);

	byte[] getExportPolicies(List<PolicyViewExportDTO> paramsMap, String type);

}
