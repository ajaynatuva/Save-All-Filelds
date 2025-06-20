package com.amps.policy.config.dto;

public class ConfigValidationDTO {
	
	private String selectedType;
	
	private String policyId;
	
	private String policyNumber;
	
	private String policyVersion;
	
	private String emailId;

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyVersion() {
		return policyVersion;
	}

	public void setPolicyVersion(String policyVersion) {
		this.policyVersion = policyVersion;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "ConfigValidationDTO [selectedType=" + selectedType + ", policyId=" + policyId + ", policyNumber="
				+ policyNumber + ", policyVersion=" + policyVersion + ", emailId=" + emailId + "]";
	}
	
	
	
	
}
