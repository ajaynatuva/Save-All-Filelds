package com.amps.policy.config.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cond_code_lkp", schema = "policy")
public class ConditionCodeLookUp {
	@Id
	@SequenceGenerator(name = "policy.cond_code_id_seq", sequenceName = "policy.cond_code_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.cond_code_id_seq")
	@Column(name = "cond_id", updatable = false)
	private Integer condId;

	@Column(name = "cond_code")
	private String condCode;

	@Column(name = "cond_desc")
	private String condDesc;

	@Column(name = "priority")
	private Integer priority;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	public Integer getCondId() {
		return condId;
	}

	public void setCondId(Integer condId) {
		this.condId = condId;
	}

	public String getCondCode() {
		return condCode;
	}

	public void setCondCode(String condCode) {
		this.condCode = condCode;
	}

	public String getCondDesc() {
		return condDesc;
	}

	public void setCondDesc(String condDesc) {
		this.condDesc = condDesc;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date date) {
		this.startDate = date;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date date) {
		this.endDate = date;
	}

}