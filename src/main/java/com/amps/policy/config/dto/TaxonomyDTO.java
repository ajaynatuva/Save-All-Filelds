package com.amps.policy.config.dto;

public class TaxonomyDTO {
    private Integer loaderKey;

    private Integer policyId;

    private Integer clientGroupId;

    private String taxonomyCode;

    private String specCode;

    private String subSpecCode;

    private String subSpecDesc;

    private Integer function;

    private String reason;

    private Integer deletedB;

    private String createdDate;

    private String updatedDate;

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

    public Integer getDeletedB() {
        return deletedB;
    }

    public void setDeletedB(Integer deletedB) {
        this.deletedB = deletedB;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

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

    public String getTaxonomyCode() {
        return taxonomyCode;
    }

    public void setTaxonomyCode(String taxonomyCode) {
        this.taxonomyCode = taxonomyCode;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getSubSpecCode() {
        return subSpecCode;
    }

    public void setSubSpecCode(String subSpecCode) {
        this.subSpecCode = subSpecCode;
    }

    public String getSubSpecDesc() {
        return subSpecDesc;
    }

    public void setSubSpecDesc(String subSpecDesc) {
        this.subSpecDesc = subSpecDesc;
    }

    public Integer getFunction() {
        return function;
    }

    public void setFunction(Integer function) {
        this.function = function;
    }
}
