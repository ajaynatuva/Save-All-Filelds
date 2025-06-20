package com.amps.policy.config.model;

import jakarta.persistence.*;

@Entity
@Table(name = "policy_condition_code", schema = "policy")
public class PolicyConditionCode {

	@Id
	@SequenceGenerator(name = "policy.policy_condition_type_policy_id_seq", sequenceName = "policy.policy_condition_type_policy_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.policy_condition_type_policy_id_seq")
	@Column(name = "policy_condition_code_key")
	private Integer policyConditionCodeKey;

	@Column(name = "policy_id")
	private Integer policyId;

	@Column(name = "condition_code")
	private String conditionCode;

	@Column(name = "condition_code_desc")
	private String conditionCodeDesc;

	public Integer getPolicyConditionCodeKey() {
		return policyConditionCodeKey;
	}

	public void setPolicyConditionCodeKey(Integer policyConditionCodeKey) {
		this.policyConditionCodeKey = policyConditionCodeKey;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getConditionCode() {
		return conditionCode;
	}

	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}

	public String getConditionCodeDesc() {
		return conditionCodeDesc;
	}

	public void setConditionCodeDesc(String conditionCodeDesc) {
		this.conditionCodeDesc = conditionCodeDesc;
	}

}
