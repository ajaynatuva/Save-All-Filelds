package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bw_type_lkp", schema = "source")
public class BwTypeLookUp {
	
	@Id
	@Column(name = "bw_type_key")
	private Integer bwTypeKey;

	@Column(name = "description")
	private String description;

	public Integer getBwTypeKey() {
		return bwTypeKey;
	}

	public void setBwTypeKey(Integer bwTypeKey) {
		this.bwTypeKey = bwTypeKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
