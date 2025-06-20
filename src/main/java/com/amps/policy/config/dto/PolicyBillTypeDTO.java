package com.amps.policy.config.dto;

public class PolicyBillTypeDTO {

	private Integer policyId;

	private String billType;

	private String billTypeDesc;

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

}
