package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "policy_cpt_action_lkp", schema = "policy")
public class PolicyActionCptLookUp {

	@Id
	@Column(name = "policy_cpt_action_key")
	private Integer policyCptActionKey;

	@Column(name = "policy_cpt_action_code")
	private String policyCptActionCode;

	@Column(name = "policy_cpt_action_desc")
	private String policyCptActionDesc;

	public String getPolicyCptActionCode() {
		return policyCptActionCode;
	}

	public void setPolicyCptActionCode(String policyCptActionCode) {
		this.policyCptActionCode = policyCptActionCode;
	}

	public String getPolicyCptActionDesc() {
		return policyCptActionDesc;
	}

	public Integer getPolicyCptActionKey() {
		return policyCptActionKey;
	}

	public void setPolicyCptActionKey(Integer policyCptActionKey) {
		this.policyCptActionKey = policyCptActionKey;
	}

	public void setPolicyCptActionDesc(String policyCptActionDesc) {
		this.policyCptActionDesc = policyCptActionDesc;
	}

}
