package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "modifier_pay_percentage_lkp", schema = "policy")
public class ModifierPayPercentageLkp {
	@Id
	@Column(name = "MPP_KEY")
	private Integer mppKey;
	
	@Column(name = "DESCRIPTION")
	private String description;

	public Integer getMppKey() {
		return mppKey;
	}

	public void setMppKey(Integer mppKey) {
		this.mppKey = mppKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
