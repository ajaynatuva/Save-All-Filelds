package com.amps.policy.config.dto;

import java.util.Date;

public class PolicyUploadDTO {

	private String cptCode;

	private String sameOrSimCode;

	private Date dosFrom;

	private Date dosTo;

	public String getCptCode() {
		return cptCode;
	}

	public void setCptCode(String cptCode) {
		this.cptCode = cptCode;
	}

	public String getSameOrSimCode() {
		return sameOrSimCode;
	}

	public void setSameOrSimCode(String sameOrSimCode) {
		this.sameOrSimCode = sameOrSimCode;
	}

	public Date getDosFrom() {
		return dosFrom;
	}

	public void setDosFrom(Date dosFrom) {
		this.dosFrom = dosFrom;
	}

	public Date getDosTo() {
		return dosTo;
	}

	public void setDosTo(Date dosTo) {
		this.dosTo = dosTo;
	}

	@Override
	public String toString() {
		return "PolicyUploadDTO [cptCode=" + cptCode + ", sameOrSimCode=" + sameOrSimCode + ", dosFrom=" + dosFrom
				+ ", dosTo=" + dosTo + "]";
	}

}
