package com.amps.policy.config.dto;

public class TaxIdDeltaDTO {
    private String delta_action;
    private Integer policyId;
    private Integer clientGroupId;
    private String lob;
    private String claimType;
    private Integer taxId;
    private String reason;

    public String getDelta_action() {
        return delta_action;
    }

    public void setDelta_action(String delta_action) {
        this.delta_action = delta_action;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getClientGroupId() {
        return clientGroupId;
    }

    public void setClientGroupId(Integer clientGroupId) {
        this.clientGroupId = clientGroupId;
    }

    public String getLob() {
        return lob;
    }

    public void setLob(String lob) {
        this.lob = lob;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
