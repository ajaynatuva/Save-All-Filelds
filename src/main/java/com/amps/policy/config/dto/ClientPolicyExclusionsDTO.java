package com.amps.policy.config.dto;

public class ClientPolicyExclusionsDTO {

	private Integer policyId;

	private Integer policyNumber;

	private Integer policyVersion;

	private Integer medicalPolicyKeyFk;

	private Integer subPolicyKeyFk;

	private Integer clientGroupId;

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public Integer getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Integer policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Integer getPolicyVersion() {
		return policyVersion;
	}

	public void setPolicyVersion(Integer policyVersion) {
		this.policyVersion = policyVersion;
	}

	public Integer getMedicalPolicyKeyFk() {
		return medicalPolicyKeyFk;
	}

	public void setMedicalPolicyKeyFk(Integer medicalPolicyKeyFk) {
		this.medicalPolicyKeyFk = medicalPolicyKeyFk;
	}

	public Integer getSubPolicyKeyFk() {
		return subPolicyKeyFk;
	}

	public void setSubPolicyKeyFk(Integer subPolicyKeyFk) {
		this.subPolicyKeyFk = subPolicyKeyFk;
	}

	public Integer getClientGroupId() {
		return clientGroupId;
	}

	public void setClientGroupId(Integer clientGroupId) {
		this.clientGroupId = clientGroupId;
	}

}
