package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "medical_policies", schema = "policy")
public class MedicalPolicyLookup {
	@Id
	@Column(name = "medical_policy_key")
	private Integer medicalPolicyKey;

	@Column(name = "medical_policy_title")
	private String medicalPolicyTitle;

	@Column(name = "medical_policy_desc")
	private String medicalPolicyDesc;

	@Column(name = "last_updated_ts")
	private Date lastUpdatedTs;

	@JsonIgnore
	@Column(name = "medical_policy_summary")
	private String medicalPolicySummary;

	public MedicalPolicyLookup() {

	}

	public Integer getMedicalPolicyKey() {
		return medicalPolicyKey;
	}

	public void setMedicalPolicyKey(Integer medicalPolicyKey) {
		this.medicalPolicyKey = medicalPolicyKey;
	}

	public String getMedicalPolicyTitle() {
		return medicalPolicyTitle;
	}

	public void setMedicalPolicyTitle(String medicalPolicyTitle) {
		this.medicalPolicyTitle = medicalPolicyTitle;
	}

	public String getMedicalPolicyDesc() {
		return medicalPolicyDesc;
	}

	public void setMedicalPolicyDesc(String medicalPolicyDesc) {
		this.medicalPolicyDesc = medicalPolicyDesc;
	}

	public Date getLastUpdatedTs() {
		return lastUpdatedTs;
	}

	public void setLastUpdatedTs(Date lastUpdatedTs) {
		this.lastUpdatedTs = lastUpdatedTs;
	}

	public String getMedicalPolicySummary() {
		return medicalPolicySummary;
	}

	public void setMedicalPolicySummary(String medicalPolicySummary) {
		this.medicalPolicySummary = medicalPolicySummary;
	}

	@Override
	public String toString() {
		return "MedicalPolicyLookup [medicalPolicyKey=" + medicalPolicyKey + ", medicalPolicyTitle="
				+ medicalPolicyTitle + ", medicalPolicyDesc=" + medicalPolicyDesc + ", lastUpdatedTs=" + lastUpdatedTs
				+ ", medicalPolicySummary=" + medicalPolicySummary + "]";
	}

}
