package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tax_link_lkp", schema = "policy")
public class TaxLinkLookUp {

	@Id
	@Column(name = "tax_link_lkp_key")
	private Integer taxLinkLkpKey;

	@Column(name = "description")
	private String description;

    public TaxLinkLookUp() {
    }

    public Integer getTaxLinkLkpKey() {
		return taxLinkLkpKey;
	}

	public void setTaxLinkLkpKey(Integer taxLinkLkpKey) {
		this.taxLinkLkpKey = taxLinkLkpKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
