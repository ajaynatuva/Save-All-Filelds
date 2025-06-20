package com.amps.policy.config.dto;

public class ClientGroupExclusionDTO {

	private Integer policyId;
	
	private Integer clientGroupId;

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public Integer getClientGroupId() {
		return clientGroupId;
	}

	public void setClientGroupId(Integer clientGroupId) {
		this.clientGroupId = clientGroupId;
	}

	@Override
	public String toString() {
		return "ClientGroupExclusionDTO [policyId=" + policyId + ", clientGroupId=" + clientGroupId + "]";
	}
}
