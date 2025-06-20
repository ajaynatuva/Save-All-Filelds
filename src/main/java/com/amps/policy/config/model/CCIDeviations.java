package com.amps.policy.config.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "cci_deviations", schema = "source")
public class CCIDeviations {

//	@EmbeddedId
//	private CCIDeviationsId cciDeviationsId;
	@Column(name = "cci_key")
	private Integer cciKey;

	@Column(name = "column_i")
	private String columnI;

	@Column(name = "column_ii")
	private String columnII;
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "client_group_id")
	private Integer clientGroupId;
	@Id
	@SequenceGenerator(name = "source.deviation_key_seq", sequenceName = "source.deviation_key_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "source.deviation_key_seq")
	@Column(name = "deviations_key")
	private Integer deviationsKey;
	@Column(name = "lob")
	private String lobKey;

	@Column(name = "claim_type")
	private String claimType;

	@Column(name = "cci_rationale_key")
	private Integer cciRationaleKey;

	@Column(name = "allow_mod_b")
	private Integer allowModB;

	@Column(name = "dev_start_date")
	private Date devStartDate;

	@Column(name = "dev_end_date")
	private Date devEndDate;

	@Column(name = "comments", columnDefinition = "TEXT")
	private String comments;

	@Column(name = "deleted_b")
	private boolean deletedB;

	@Column(name = "jira_id")
	private String jiraId;

	@Column(name = "jira_desc")
	private String jiraDesc;
	
	@Column(name = "user_id")
	private String userId;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "state")
	private String state;

//	public CCIDeviationsId getCciDeviationsId() {
//		return cciDeviationsId;
//	}
//
//	public void setCciDeviationsId(CCIDeviationsId cciDeviationsId) {
//		this.cciDeviationsId = cciDeviationsId;
//	}


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

	public Integer getClientGroupId() {
		return clientGroupId;
	}

	public void setClientGroupId(Integer clientGroupId) {
		this.clientGroupId = clientGroupId;
	}

	public Integer getDeviationsKey() {
		return deviationsKey;
	}

	public void setDeviationsKey(Integer deviationsKey) {
		this.deviationsKey = deviationsKey;
	}

	public String getLobKey() {
		return lobKey;
	}

	public void setLobKey(String lobKey) {
		this.lobKey = lobKey;
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

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
