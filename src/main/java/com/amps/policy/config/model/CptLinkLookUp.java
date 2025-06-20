package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cpt_link_lkp", schema = "policy")
public class CptLinkLookUp {

	@Id
	@Column(name = "cpt_link_lkp_key")
	private Integer cptLinkLkpKey;

	@Column(name = "description")
	private String description;

    public CptLinkLookUp() {
    }

    public Integer getCptLinkLkpKey() {
		return cptLinkLkpKey;
	}

	public void setCptLinkLkpKey(Integer cptLinkLkpKey) {
		this.cptLinkLkpKey = cptLinkLkpKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}