package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "revenue_code_claim_link", schema = "policy")

public class RevenueCodeClaimLink {

	@Id
	@Column(name = "revenue_code_claim_link_key")
	private Integer revenueCodeClaimLinkKey;

	@Column(name = "description")
	private String description;

    public RevenueCodeClaimLink() {
    }

    public Integer getRevenueCodeClaimLinkKey() {
		return revenueCodeClaimLinkKey;
	}

	public void setRevenueCodeClaimLinkKey(Integer revenueCodeClaimLinkKey) {
		this.revenueCodeClaimLinkKey = revenueCodeClaimLinkKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
