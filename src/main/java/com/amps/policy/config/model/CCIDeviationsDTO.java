package com.amps.policy.config.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CCIDeviationsDTO {


    private Integer clientGroupId;

    private String lobKey;

    private String claimType;

    private Integer cciKey;

    private String columnI;
    
	private String state;

    private String columnII;

    private Date startDate;

    private Date endDate;

    private Integer cciRationaleKey;
    
	private Integer deviationsKey;

    private Integer allowModB;

    private Date devStartDate;


    private Date devEndDate;


    private String comments;

    private boolean deletedB;


    private String jiraId;


    private String jiraDesc;


    private String userId;


    private Date createdDate;


    private Date updatedDate;

    public Integer getClientGroupId() {
        return clientGroupId;
    }

    public void setClientGroupId(Integer clientGroupId) {
        this.clientGroupId = clientGroupId;
    }

    public String getLobKey() {
        return lobKey;
    }

    public void setLobKey(String lobKey) {
        this.lobKey = lobKey;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public Integer getCciKey() {
        return cciKey;
    }

    public void setCciKey(Integer cciKey) {
        this.cciKey = cciKey;
    }

    public String getColumnI() {
        return columnI;
    }

    public void setColumnI(String columnI) {
        this.columnI = columnI;
    }

    public String getColumnII() {
        return columnII;
    }

    public void setColumnII(String columnII) {
        this.columnII = columnII;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getCciRationaleKey() {
        return cciRationaleKey;
    }

    public void setCciRationaleKey(Integer cciRationaleKey) {
        this.cciRationaleKey = cciRationaleKey;
    }

    public Integer getAllowModB() {
        return allowModB;
    }

    public void setAllowModB(Integer allowModB) {
        this.allowModB = allowModB;
    }

    public Date getDevStartDate() {
        return devStartDate;
    }

    public void setDevStartDate(Date devStartDate) {
        this.devStartDate = devStartDate;
    }

    public Date getDevEndDate() {
        return devEndDate;
    }

    public void setDevEndDate(Date devEndDate) {
        this.devEndDate = devEndDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isDeletedB() {
        return deletedB;
    }

    public void setDeletedB(boolean deletedB) {
        this.deletedB = deletedB;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getState() {return state;}

    public void setState(String state) {this.state = state;}

    public Integer getDeviationsKey() {return deviationsKey;}

    public void setDeviationsKey(Integer deviationsKey) {this.deviationsKey = deviationsKey;}
}

