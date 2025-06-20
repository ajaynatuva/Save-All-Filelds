package com.amps.policy.config.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DiagnosisDTO {


	private Integer policyId;
	private String diagFrom;
	private String diagTo;
	
	@JsonFormat(pattern = "MM-dd-yyyy")
	private Date dosFrom;
	
	@JsonFormat(pattern = "MM-dd-yyyy")
	private Date dosTo;
	
	private Integer action;
	private Integer exclusion;
	private Integer headerLevel;
	private Integer principalDx;
	private Integer onlyDx;

	public Integer getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
	public String getDiagFrom() {
		return diagFrom;
	}
	public void setDiagFrom(String diagFrom) {
		this.diagFrom = diagFrom;
	}
	public String getDiagTo() {
		return diagTo;
	}
	public void setDiagTo(String diagTo) {
		this.diagTo = diagTo;
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
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public Integer getExclusion() {
		return exclusion;
	}
	public void setExclusion(Integer exclusion) {
		this.exclusion = exclusion;
	}
	public Integer getHeaderLevel() {
		return headerLevel;
	}
	public void setHeaderLevel(Integer headerLevel) {
		this.headerLevel = headerLevel;
	}
	public Integer getPrincipalDx() {
		return principalDx;
	}
	public void setPrincipalDx(Integer principalDx) {
		this.principalDx = principalDx;
	}
	public Integer getOnlyDx() {
		return onlyDx;
	}

	public void setOnlyDx(Integer onlyDx) {
		this.onlyDx = onlyDx;
	}
}
