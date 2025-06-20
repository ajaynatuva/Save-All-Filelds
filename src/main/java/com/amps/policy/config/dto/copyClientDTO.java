package com.amps.policy.config.dto;

public class copyClientDTO {

	Integer clientGroupId;
	Integer policyId;
	String clientStartDate;
	String clientEndDate;
	String type;

	public Integer getClientGroupId() {
		return clientGroupId;
	}

	public void setClientGroupId(Integer clientGroupId) {
		this.clientGroupId = clientGroupId;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getClientStartDate() {
		return clientStartDate;
	}

	public void setClientStartDate(String clientStartDate) {
		this.clientStartDate = clientStartDate;
	}

	public String getClientEndDate() {
		return clientEndDate;
	}

	public void setClientEndDate(String clientEndDate) {
		this.clientEndDate = clientEndDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
