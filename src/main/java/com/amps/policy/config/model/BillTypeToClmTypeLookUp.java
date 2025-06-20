package com.amps.policy.config.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BILL_TYPE_TO_CLM_TYPE", schema = "ipuclaims")
public class BillTypeToClmTypeLookUp {
	@Id
	@Column(name = "BILL_TYPE")
	private String billType;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CLAIM_TYPE_MATCH")
	private String claimTypeMatch;

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClaimTypeMatch() {
		return claimTypeMatch;
	}

	public void setClaimTypeMatch(String claimTypeMatch) {
		this.claimTypeMatch = claimTypeMatch;
	}


	
}