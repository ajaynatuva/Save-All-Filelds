package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "npi_link_lkp", schema = "policy")
public class NpiLinkLookUp {

	@Id
	@Column(name = "npi_link_lkp_key")
	private Integer npiLinkLkpKey;

	@Column(name = "description")
	private String description;

	@Column(name = "product_title")
	private String product_title;

    public NpiLinkLookUp() {
    }

    public Integer getNpiLinkLkpKey() {
		return npiLinkLkpKey;
	}

	public void setNpiLinkLkpKey(Integer npiLinkLkpKey) {
		this.npiLinkLkpKey = npiLinkLkpKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

}