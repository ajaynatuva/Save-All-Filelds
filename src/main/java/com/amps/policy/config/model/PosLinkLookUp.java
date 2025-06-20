package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pos_link_lkp", schema = "policy")

public class PosLinkLookUp {

	@Id
	@Column(name = "pos_link_lkp_key")
	private Integer posLinkLkpKey;

	@Column(name = "description")
	private String description;

    public PosLinkLookUp() {
    }

    public Integer getPosLinkLkpKey() {
		return posLinkLkpKey;
	}

	public void setPosLinkLkpKey(Integer posLinkLkpKey) {
		this.posLinkLkpKey = posLinkLkpKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
