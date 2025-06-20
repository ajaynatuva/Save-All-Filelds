package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "max_units_type", schema = "source")
public class MaxUnitsTypes {
	
	@Id
	@Column(name = "max_units_type_key")
	private Integer maxUnitsTypeKey;

	@Column(name = "max_units_type_desc")
	private String maxUnitsTypeDesc;

	public Integer getMaxUnitsTypeKey() {
		return maxUnitsTypeKey;
	}

	public void setMaxUnitsTypeKey(Integer maxUnitsTypeKey) {
		this.maxUnitsTypeKey = maxUnitsTypeKey;
	}

	public String getMaxUnitsTypeDesc() {
		return maxUnitsTypeDesc;
	}

	public void setMaxUnitsTypeDesc(String maxUnitsTypeDesc) {
		this.maxUnitsTypeDesc = maxUnitsTypeDesc;
	}
	
	

}
