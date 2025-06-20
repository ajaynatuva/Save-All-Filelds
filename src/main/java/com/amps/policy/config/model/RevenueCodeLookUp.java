package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "REV_CODE_LKP", schema = "source")
public class RevenueCodeLookUp {

	@Id
	@Column(name = "REV_CODE")
	private String revCode;

	@Column(name = "REV_DESC")
	private String revDesc;

	public String getRevCode() {
		return revCode;
	}

	public void setRevCode(String revCode) {
		this.revCode = revCode;
	}

	public String getRevDesc() {
		return revDesc;
	}

	public void setRevDesc(String revDesc) {
		this.revDesc = revDesc;
	}

}