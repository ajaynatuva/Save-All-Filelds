package com.amps.policy.config.dto;

public class TaxIdDTO {
    private Integer loaderKey;
    private Integer policyId;
    private Integer clientGroupId;
    private String lob;
    private String claimType;
    private Integer taxId;
    private Integer deletedB;
    private String createdDate;
    private String updatedDate;

    public Integer getLoaderKey() {
        return loaderKey;
    }

    public void setLoaderKey(Integer loaderKey) {
        this.loaderKey = loaderKey;
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

    public Integer getDeletedB() {
        return deletedB;
    }

    public void setDeletedB(Integer deletedB) {
        this.deletedB = deletedB;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
