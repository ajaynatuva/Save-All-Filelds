package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "bill_type_lkp", schema = "source")
public class BillTypeLookUp {
	@Id
	@Column(name = "bill_type")
	private String billType;

	@Column(name = "bill_type_desc")
	private String billTypeDesc;

	@Column(name = "inpatient_b")
	private Integer inpatientB;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name="claim_type_match")
	private String claimTypeMatch;

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillTypeDesc() {
		return billTypeDesc;
	}

	public void setBillTypeDesc(String billTypeDesc) {
		this.billTypeDesc = billTypeDesc;
	}

	public Integer getInpatientB() {
		return inpatientB;
	}

	public void setInpatientB(Integer inpatientB) {
		this.inpatientB = inpatientB;
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

	public String getClaimTypeMatch() {
		return claimTypeMatch;
	}

	public void setClaimTypeMatch(String claimTypeMatch) {
		this.claimTypeMatch = claimTypeMatch;
	}
	
}