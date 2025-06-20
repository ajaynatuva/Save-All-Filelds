package com.amps.policy.config.dao;

import com.amps.policy.config.dto.CategoryDTO;
import com.amps.policy.config.dto.PolicyViewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PolicyViewDao {

	List<CategoryDTO> getCategoryData();

	public List<PolicyViewDTO> searchCriteria(List<PolicyViewDTO> policyDto);

	public List<PolicyViewDTO> getSubPolicyByCat(List<PolicyViewDTO> policyDto);

	public List<PolicyViewDTO> getReasonPolicies(List<PolicyViewDTO> policyDto);

	public List<PolicyViewDTO> getTotalPolicies(List<PolicyViewDTO> policyDto);
	
	void  insertClaimIntroFileToDb(String filePath);
	
	void  insertSummaryFilesToDb(String filePath,String FileName);
}
