package com.amps.policy.config.dto;

import java.util.List;

import com.amps.policy.config.model.ClientAssignment;
import com.amps.policy.config.model.Diagnosis;
import com.amps.policy.config.model.PolicyBillType;
import com.amps.policy.config.model.PolicyConditionCode;
import com.amps.policy.config.model.Procedures;

public class CustomPolicyDTO {

	List<Diagnosis> diagnosisData;

	List<PolicyBillType> policyBillTypeData;

	List<PolicyConditionCode> policyConditionCodeData;

	List<ClientAssignment> clientAssignmentData;

	List<ProceduresDTO> proceduresData;

	public List<Diagnosis> getDiagnosisData() {
		return diagnosisData;
	}

	public void setDiagnosisData(List<Diagnosis> diagnosisData) {
		this.diagnosisData = diagnosisData;
	}

	public List<PolicyBillType> getPolicyBillTypeData() {
		return policyBillTypeData;
	}

	public void setPolicyBillTypeData(List<PolicyBillType> policyBillTypeData) {
		this.policyBillTypeData = policyBillTypeData;
	}

	public List<PolicyConditionCode> getPolicyConditionCodeData() {
		return policyConditionCodeData;
	}

	public void setPolicyConditionCodeData(List<PolicyConditionCode> policyConditionCodeData) {
		this.policyConditionCodeData = policyConditionCodeData;
	}

	public List<ClientAssignment> getClientAssignmentData() {
		return clientAssignmentData;
	}

	public void setClientAssignmentData(List<ClientAssignment> clientAssignmentData) {
		this.clientAssignmentData = clientAssignmentData;
	}

	public List<ProceduresDTO> getProceduresData() {
		return proceduresData;
	}

	public void setProceduresData(List<ProceduresDTO> proceduresData) {
		this.proceduresData = proceduresData;
	}

	@Override
	public String toString() {
		return "CustomPolicyDTO [diagnosisData=" + diagnosisData + ", policyBillTypeData=" + policyBillTypeData
				+ ", policyConditionCodeData=" + policyConditionCodeData + ", clientAssignmentData="
				+ clientAssignmentData + ", proceduresData=" + proceduresData + "]";
	}

}
