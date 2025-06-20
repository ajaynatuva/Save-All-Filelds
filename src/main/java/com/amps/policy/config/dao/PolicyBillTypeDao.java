package com.amps.policy.config.dao;

import java.util.List;

import com.amps.policy.config.dto.PolicyBillTypeDTO;
import com.amps.policy.config.model.PolicyBillType;

public interface PolicyBillTypeDao {

	void saveData(List<PolicyBillTypeDTO> policyBillTypeDTO);
	void deleteBillTypeByPolicyId(List<PolicyBillTypeDTO> policyId);
	void deletePolicyBillType(int key);
	void savePolicyBillType(List<PolicyBillType> PolicyBillType);
}
