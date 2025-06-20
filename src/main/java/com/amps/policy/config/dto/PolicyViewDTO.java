package com.amps.policy.config.dto;

public class PolicyViewDTO {
    private Integer policyNumber;
    private Integer policyId;
    private Integer policyVersion;
    private String reasonCodeFk;
    private Integer medicalPolicyKeyFk;
    private Integer subPolicyKeyFk;
    public Integer categoryFk;
    private Integer count;
    private Integer npiLogicFk;
    private Integer taxIdLogicFk;
    private Integer taxonomy;
    private Integer deactivated;
    private Integer isProdB;
    private String type;
    private String claimType;
    private String clientGroup;
    private boolean custom;

    private String medicalPolicy;
    private String subPolicy;

    public Integer getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Integer policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getReasonCodeFk() {
        return reasonCodeFk;
    }

    public void setReasonCodeFk(String reasonCodeFk) {
        this.reasonCodeFk = reasonCodeFk;
    }

    public Integer getMedicalPolicyKeyFk() {
        return medicalPolicyKeyFk;
    }

    public void setMedicalPolicyKeyFk(Integer medicalPolicyKeyFk) {
        this.medicalPolicyKeyFk = medicalPolicyKeyFk;
    }

    public Integer getSubPolicyKeyFk() {
        return subPolicyKeyFk;
    }

    public void setSubPolicyKeyFk(Integer subPolicyKeyFk) {
        this.subPolicyKeyFk = subPolicyKeyFk;
    }


    public Integer getCategoryFk() {
        return categoryFk;
    }

    public void setCategoryFk(Integer categoryFk) {
        this.categoryFk = categoryFk;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNpiLogicFk() {
        return npiLogicFk;
    }

    public void setNpiLogicFk(Integer npiLogicFk) {
        this.npiLogicFk = npiLogicFk;
    }

    public Integer getTaxIdLogicFk() {
        return taxIdLogicFk;
    }

    public void setTaxIdLogicFk(Integer taxIdLogicFk) {
        this.taxIdLogicFk = taxIdLogicFk;
    }

    public Integer getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(Integer taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Integer getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Integer deactivated) {
        this.deactivated = deactivated;
    }

    public Integer getPolicyVersion() {
        return policyVersion;
    }

    public void setPolicyVersion(Integer policyVersion) {
        this.policyVersion = policyVersion;
    }

    public Integer getIsProdB() {
        return isProdB;
    }

    public void setIsProdB(Integer isProdB) {
        this.isProdB = isProdB;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getClientGroup() {
        return clientGroup;
    }

    public void setClientGroup(String clientGroup) {
        this.clientGroup = clientGroup;
    }

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public String getMedicalPolicy() {
        return medicalPolicy;
    }

    public void setMedicalPolicy(String medicalPolicy) {
        this.medicalPolicy = medicalPolicy;
    }

    public String getSubPolicy() {
        return subPolicy;
    }

    public void setSubPolicy(String subPolicy) {
        this.subPolicy = subPolicy;
    }
}
