package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "change_modifier_lkp", schema = "policy")
public class ChangeModifierLookUp {

	@Id
	@Column(name = "change_modifier_key")
	private Integer changeModifierKey;

	@Column(name = "description")
	private String description;

	public Integer getChangeModifierKey() {
		return changeModifierKey;
	}

	public void setChangeModifierKey(Integer changeModifierKey) {
		this.changeModifierKey = changeModifierKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
