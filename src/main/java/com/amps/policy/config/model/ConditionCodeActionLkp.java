package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "policy_cond_code_action_lkp", schema = "policy")
public class ConditionCodeActionLkp {

	@Id
	@Column(name = "cond_code_key")
	private Integer condCodeKey;

	@Column(name = "condition_code")
	private String conditionCode;

	@Column(name = "condition_code_desc")
	private String conditionCodeDesc;

	@Column(name = "deleted_b")
	private Integer deleted_b;

    public ConditionCodeActionLkp() {
    }

    public Integer getCondCodeKey() {
		return condCodeKey;
	}

	public void setCondCodeKey(Integer condCodeKey) {
		this.condCodeKey = condCodeKey;
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

	public Integer getDeleted_b() {
		return deleted_b;
	}

	public void setDeleted_b(Integer deleted_b) {
		this.deleted_b = deleted_b;
	}

}
