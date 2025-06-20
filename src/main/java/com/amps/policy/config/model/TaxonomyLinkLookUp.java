package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "taxonomy_link_lkp", schema = "policy")
public class TaxonomyLinkLookUp {

	@Id
	@Column(name = "taxonomy_link_lkp_key")
	private Integer taxonomyLinkLkpKey;

	@Column(name = "description")
	private String description;

    public TaxonomyLinkLookUp() {
    }

    public Integer getTaxonomyLinkLkpKey() {
		return taxonomyLinkLkpKey;
	}

	public void setTaxonomyLinkLkpKey(Integer taxonomyLinkLkpKey) {
		this.taxonomyLinkLkpKey = taxonomyLinkLkpKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
