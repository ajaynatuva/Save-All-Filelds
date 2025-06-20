package com.amps.policy.config.dto;

public class PolicySearchResultDTO {

	private Integer policyId;

	private Integer policyNumber;

	private Integer policyVersion;

	private String policyDesc;

	private Integer medicalPolicyKeyFk;

	private Integer subPolicyKeyFk;

	private Integer categoryFk;

	private Boolean custom;

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

	public String getPolicyDesc() {
		return policyDesc;
	}

	public void setPolicyDesc(String policyDesc) {
		this.policyDesc = policyDesc;
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

	public Integer getCategoryFk() {
		return categoryFk;
	}

	public void setCategoryFk(Integer categoryFk) {
		this.categoryFk = categoryFk;
	}

	public Boolean getCustom() {
		return custom;
	}

	public void setCustom(Boolean custom) {
		this.custom = custom;
	}

}
