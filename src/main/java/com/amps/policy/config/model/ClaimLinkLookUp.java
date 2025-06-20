package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "claim_link_lkp", schema = "policy")
public class ClaimLinkLookUp {

    @Id
    @Column(name = "claim_link_key")
    private Integer claimLinkKey;

    @Column(name = "claim_link_code")
    private String claimLinkCode;

    @Column(name = "claim_link_desc")
    private String claimLinkDesc;

    public Integer getClaimLinkKey() {
        return claimLinkKey;
    }

    public void setClaimLinkKey(Integer claimLinkKey) {
        this.claimLinkKey = claimLinkKey;
    }

    public String getClaimLinkCode() {
        return claimLinkCode;
    }

    public void setClaimLinkCode(String claimLinkCode) {
        this.claimLinkCode = claimLinkCode;
    }

    public String getClaimLinkDesc() {
        return claimLinkDesc;
    }

    public void setClaimLinkDesc(String claimLinkDesc) {
        this.claimLinkDesc = claimLinkDesc;
    }

}
