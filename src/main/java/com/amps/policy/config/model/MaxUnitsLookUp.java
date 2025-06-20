package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "max_units_lkp", schema = "source")
public class MaxUnitsLookUp {
	
	@Id
	@Column(name = "max_units_lkp_key")
	private Integer maxUnitsLkpKey;

	@Column(name = "description")
	private String Description;
	
	@Column(name = "comments")
	private String Comments;
	
	@Column(name = "max_units_type_key")
	private Integer maxUnitsTypeKey;

	@Column(name = "custom")
	private Integer custom;

	public Integer getCustom() {
		return custom;
	}

	public void setCustom(Integer custom) {
		this.custom = custom;
	}

	public Integer getMaxUnitsLkpKey() {
		return maxUnitsLkpKey;
	}

	public void setMaxUnitsLkpKey(Integer maxUnitsLkpKey) {
		this.maxUnitsLkpKey = maxUnitsLkpKey;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public Integer getMaxUnitsTypeKey() {
		return maxUnitsTypeKey;
	}

	public void setMaxUnitsTypeKey(Integer maxUnitsTypeKey) {
		this.maxUnitsTypeKey = maxUnitsTypeKey;
	}


}
