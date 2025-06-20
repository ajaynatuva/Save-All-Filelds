package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "claim_type_link_lkp", schema = "policy")
public class ClaimTypeLinkLkp {

	@Id
	@Column(name = "claim_type_link_lkp_key")
	private Integer claimTypeLinkLkpKey;

	@Column(name = "description")
	private String description;

	@Column(name = "deleted_b")
	private Integer deletedB;

    public ClaimTypeLinkLkp() {

	}
    public Integer getClaimTypeLinkLkpKey() {
		return claimTypeLinkLkpKey;
	}

	public void setClaimTypeLinkLkpKey(Integer claimTypeLinkLkpKey) {
		this.claimTypeLinkLkpKey = claimTypeLinkLkpKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDeletedB() {
		return deletedB;
	}

	public void setDeletedB(Integer deletedB) {
		this.deletedB = deletedB;
	}

}
