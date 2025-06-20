package com.amps.policy.config.dto;

public class MedicalPolicyViewResultsDTO {

	public Integer medicalPolicyKeyFk;
	
	public Integer subPolicyKeyFk;

	public Integer medicalCount;

	public Integer categoryFk;

	public Integer getMedicalPolicyKeyFk() {
		return medicalPolicyKeyFk;
	}

	public void setMedicalPolicyKeyFk(Integer medicalPolicyKeyFk) {
		this.medicalPolicyKeyFk = medicalPolicyKeyFk;
	}

	public Integer getMedicalCount() {
		return medicalCount;
	}

	public void setMedicalCount(Integer medicalCount) {
		this.medicalCount = medicalCount;
	}

	public Integer getCategoryFk() {
		return categoryFk;
	}

	public void setCategoryFk(Integer categoryFk) {
		this.categoryFk = categoryFk;
	}
	public Integer getSubPolicyKeyFk() {
		return subPolicyKeyFk;
	}

	public void setSubPolicyKeyFk(Integer subPolicyKeyFk) {
		this.subPolicyKeyFk = subPolicyKeyFk;
	}
}
