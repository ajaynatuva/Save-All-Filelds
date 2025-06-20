package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "policy_bill_type_action_lkp", schema = "policy")
public class PolicyActionBillTypeLookUp {
	
	@Id
	@Column(name = "policy_bill_type_action_key")
	private Integer policyBillTypeActionKey;
	
	@Column(name = "policy_bill_type_action_code")
	private String policyBillTypeActionCode;
	
	@Column(name = "policy_bill_type_Desc")
	private String policyBillTypeDesc;
	
	@Column(name = "deleted_b")
	private Integer deletedB;

    public PolicyActionBillTypeLookUp() {
    }

    public Integer getPolicyBillTypeActionKey() {
		return policyBillTypeActionKey;
	}

	public void setPolicyBillTypeActionKey(Integer policyBillTypeActionKey) {
		this.policyBillTypeActionKey = policyBillTypeActionKey;
	}

	public String getPolicyBillTypeActionCode() {
		return policyBillTypeActionCode;
	}

	public void setPolicyBillTypeActionCode(String policyBillTypeActionCode) {
		this.policyBillTypeActionCode = policyBillTypeActionCode;
	}

	public String getPolicyBillTypeActionDesc() {
		return policyBillTypeDesc;
	}

	public void setPolicyBillTypeActionDesc(String policyBillTypeDesc) {
		this.policyBillTypeDesc = policyBillTypeDesc;
	}

	public Integer getDeletedB() {
		return deletedB;
	}

	public void setDeletedB(Integer deletedB) {
		this.deletedB = deletedB;
	}
	
	
	
}
