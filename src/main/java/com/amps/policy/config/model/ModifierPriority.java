package com.amps.policy.config.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "modifier_priority", schema = "source")
public class ModifierPriority {

	@Id
	@Column(name = "modifier")
	private String modifier;

	@Column(name = "priority")
	private Integer priority;

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}