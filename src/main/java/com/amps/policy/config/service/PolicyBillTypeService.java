package com.amps.policy.config.service;

import com.amps.policy.config.model.BillTypeLookUp;
import com.amps.policy.config.model.Policy;
import com.amps.policy.config.model.PolicyActionBillTypeLookUp;
import com.amps.policy.config.model.PolicyBillType;
import com.amps.policy.config.model.PolicyBillTypeLinkLookUp;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PolicyBillTypeService {

	String readFileAndSaveFile(MultipartFile file);

	Integer saveToDatabase(MultipartFile file);
	
	List<BillTypeLookUp> getBillTypeLkpData();
	
	List<PolicyBillTypeLinkLookUp> getBillTypeLinkLkpData();
	
	List<PolicyBillType> getPolicyBillTypeDataById(int id);
	
	List<PolicyActionBillTypeLookUp>getPolicyBillTypeActionData();
	
	void savePolicyBillTypeData(List<PolicyBillType> PolicyBillType);
	
	void deletePolicyBillTypeData(int key);
	
	Policy getBillTypeByPolicyId(int id);
}
