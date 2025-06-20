package com.amps.policy.config.dto;

public class ConfigValidationReportDTO {

	private Integer policy_id;
	private Integer policy_number;
	private Integer policy_version;
	private String policy_desc;
	private String status;
	private String error_message;
	private Integer is_prod_b;

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public Integer getPolicy_number() {
		return policy_number;
	}

	public void setPolicy_number(Integer policy_number) {
		this.policy_number = policy_number;
	}

	public Integer getPolicy_version() {
		return policy_version;
	}

	public void setPolicy_version(Integer policy_version) {
		this.policy_version = policy_version;
	}

	public String getPolicy_desc() {
		return policy_desc;
	}

	public void setPolicy_desc(String policy_desc) {
		this.policy_desc = policy_desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public Integer getIs_prod_b() {
		return is_prod_b;
	}

	public void setIs_prod_b(Integer is_prod_b) {
		this.is_prod_b = is_prod_b;
	}

	@Override
	public String toString() {
		return "ConfigValidationReportDTO [policy_id=" + policy_id + ", policy_number=" + policy_number
				+ ", policy_version=" + policy_version + ", policy_desc=" + policy_desc + ", status=" + status
				+ ", error_message=" + error_message + ", is_prod_b=" + is_prod_b + "]";
	}

}
