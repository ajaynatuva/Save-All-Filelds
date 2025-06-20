package com.amps.policy.config.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangesDTO {

	private Integer policyId;

	private String jiraId;

	private String jiraDesc;

	private String jiraLink;

	private String userId;

	private Integer isOpenb;

	private Integer policyChangesKey;

	private Date updatedOn;

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getJiraId() {
		return jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public String getJiraDesc() {
		return jiraDesc;
	}

	public void setJiraDesc(String jiraDesc) {
		this.jiraDesc = jiraDesc;
	}

	public String getJiraLink() {
		return jiraLink;
	}

	public void setJiraLink(String jiraLink) {
		this.jiraLink = jiraLink;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getIsOpenb() {
		return isOpenb;
	}

	public void setIsOpenb(Integer isOpenb) {
		this.isOpenb = isOpenb;
	}

	public Integer getPolicyChangesKey() {
		return policyChangesKey;
	}

	public void setPolicyChangesKey(Integer policyChangesKey) {
		this.policyChangesKey = policyChangesKey;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
