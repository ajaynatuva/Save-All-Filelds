package com.amps.policy.config.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sub_policies", schema = "policy")
public class SubPolicies {
	@Id
	@Column(name = "sub_policy_key")
	private Integer subPolicyKey;

	@Column(name = "medical_policy_key")

	private Integer medicalPolicyKey;

	@Column(name = "sub_policy_title")
	private String subPolicyTitle;

	@Column(name = "sub_policy_desc")
	private String subPolicyDesc;

	@Column(name = "last_updated_ts")
	private Date lastUpdatedTs;

	@JsonIgnore
	@Column(name = "sub_policy_summary")
	private String subPolicySummary;

    public SubPolicies() {
    }

    public Integer getSubPolicyKey() {
		return subPolicyKey;
	}

	public void setSubPolicyKey(Integer subPolicyKey) {
		this.subPolicyKey = subPolicyKey;
	}

	public Integer getMedicalPolicyKey() {
		return medicalPolicyKey;
	}

	public void setMedicalPolicyKey(Integer medicalPolicyKey) {
		this.medicalPolicyKey = medicalPolicyKey;
	}

	public String getSubPolicyTitle() {
		return subPolicyTitle;
	}

	public void setSubPolicyTitle(String subPolicyTitle) {
		this.subPolicyTitle = subPolicyTitle;
	}

	public String getSubPolicyDesc() {
		return subPolicyDesc;
	}

	public void setSubPolicyDesc(String subPolicyDesc) {
		this.subPolicyDesc = subPolicyDesc;
	}

	public Date getLastUpdatedTs() {
		return lastUpdatedTs;
	}

	public void setLastUpdatedTs(Date lastUpdatedTs) {
		this.lastUpdatedTs = lastUpdatedTs;
	}

	public String getSubPolicySummary() {
		return subPolicySummary;
	}

	public void setSubPolicySummary(String subPolicySummary) {
		this.subPolicySummary = subPolicySummary;
	}

}
