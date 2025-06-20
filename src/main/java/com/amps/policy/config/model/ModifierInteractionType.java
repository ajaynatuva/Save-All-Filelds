package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "modifier_interaction_type", schema = "source")
public class ModifierInteractionType {

	@Id
	@Column(name = "mit_key")
	private Integer mitKey;
	
	@Column(name = "mit_desc")
	private String mitDesc;

	public Integer getMitKey() {
		return mitKey;
	}

	public void setMitKey(Integer mitKey) {
		this.mitKey = mitKey;
	}

	public String getMitDesc() {
		return mitDesc;
	}

	public void setMitDesc(String mitDesc) {
		this.mitDesc = mitDesc;
	}
	
	
}
