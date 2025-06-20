package com.amps.policy.config.dto;

public class TaxonomyDeltaDTO {

	private String delta_action;

	private Integer policyId;

	private Integer clientGroupId;

	private String taxonomyCode;

	private String specCode;

	private String subspecCode;

	private String subspecDesc;

	private Integer function;

	private String reason;

	public String getDelta_action() {
		return delta_action;
	}

	public void setDelta_action(String delta_action) {
		this.delta_action = delta_action;
	}

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

	public String getTaxonomyCode() {
		return taxonomyCode;
	}

	public void setTaxonomyCode(String taxonomyCode) {
		this.taxonomyCode = taxonomyCode;
	}

	public String getSpecCode() {
		return specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

	public String getSubspecCode() {
		return subspecCode;
	}

	public void setSubspecCode(String subspecCode) {
		this.subspecCode = subspecCode;
	}

	public String getSubspecDesc() {
		return subspecDesc;
	}

	public void setSubspecDesc(String subspecDesc) {
		this.subspecDesc = subspecDesc;
	}

	public Integer getFunction() {
		return function;
	}

	public void setFunction(Integer function) {
		this.function = function;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
