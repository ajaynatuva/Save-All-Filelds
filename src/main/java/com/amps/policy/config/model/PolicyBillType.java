package com.amps.policy.config.model;

import jakarta.persistence.*;

@Entity
@Table(name = "policy_bill_types", schema = "policy")
public class PolicyBillType {
	@Id
	@SequenceGenerator(name = "policy.policy_bill_type_policy_id_seq", sequenceName = "policy.policy_bill_type_policy_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.policy_bill_type_policy_id_seq")
	@Column(name = "policy_bill_type_key")
	private Integer policyBillTypeKey;
	
	@Column(name = "policy_id")
	private Integer policyId;
	
	@Column(name = "bill_type")
	private String billType;
	
	@Column(name = "bill_type_desc")
	private String billTypeDesc;

	public Integer getPolicyBillTypeKey() {
		return policyBillTypeKey;
	}

	public void setPolicyBillTypeKey(Integer policyBillTypeKey) {
		this.policyBillTypeKey = policyBillTypeKey;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillTypeDesc() {
		return billTypeDesc;
	}

	public void setBillTypeDesc(String billTypeDesc) {
		this.billTypeDesc = billTypeDesc;
	}

	@Override
	public String toString() {
		return "PolicyBillType [policyBillTypeKey=" + policyBillTypeKey + ", policyId=" + policyId + ", billType="
				+ billType + ", billTypeDesc=" + billTypeDesc + "]";
	}

	
	
	

	
}
