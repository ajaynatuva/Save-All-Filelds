package com.amps.policy.config.dto;

public class PolicyViewExportDTO {

    private Integer PolicyNumber;
    private String reasonCodeFk;
    private String reasonCodeDesc;
    private String medicalDesc;
    private String subDesc;
    public String categoryDesc;
    private String npiDesc;
    private String taxDesc;
    private String taxonomyDesc;
    private Integer deactivated;
    private String notes;
    private String claimType;
    private String policyDesc;
    private Integer priority;
    private String minAgeDesc;
    private String maxAgeDesc;
    private String reference;
    private String refSourceDesc;
    private byte[] medicalPolicySummary;
    private byte[] subPolicySummary;

    private  Integer medicalPolicyKeyFk;
    private  Integer subPolicyKeyFk;


    public byte[] getMedicalPolicySummary() {
        return medicalPolicySummary;
    }

    public void setMedicalPolicySummary(byte[] medicalPolicySummary) {
        this.medicalPolicySummary = medicalPolicySummary;
    }

    public byte[] getSubPolicySummary() {
        return subPolicySummary;
    }

    public void setSubPolicySummary(byte[] subPolicySummary) {
        this.subPolicySummary = subPolicySummary;
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

    public Integer getPolicyNumber() {
        return PolicyNumber;
    }

    public void setPolicyNumber(Integer policyNumber) {
        PolicyNumber = policyNumber;
    }

    public String getReasonCodeFk() {
        return reasonCodeFk;
    }

    public void setReasonCodeFk(String reasonCodeFk) {
        this.reasonCodeFk = reasonCodeFk;
    }

    public String getReasonCodeDesc() {
        return reasonCodeDesc;
    }

    public void setReasonCodeDesc(String reasonCodeDesc) {
        this.reasonCodeDesc = reasonCodeDesc;
    }

    public String getMedicalDesc() {
        return medicalDesc;
    }

    public void setMedicalDesc(String medicalDesc) {
        this.medicalDesc = medicalDesc;
    }

    public String getSubDesc() {
        return subDesc;
    }

    public void setSubDesc(String subDesc) {
        this.subDesc = subDesc;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getNpiDesc() {
        return npiDesc;
    }

    public void setNpiDesc(String npiDesc) {
        this.npiDesc = npiDesc;
    }

    public String getTaxDesc() {
        return taxDesc;
    }

    public void setTaxDesc(String taxDesc) {
        this.taxDesc = taxDesc;
    }

    public String getTaxonomyDesc() {
        return taxonomyDesc;
    }

    public void setTaxonomyDesc(String taxonomyDesc) {
        this.taxonomyDesc = taxonomyDesc;
    }

    public Integer getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Integer deactivated) {
        this.deactivated = deactivated;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getPolicyDesc() {
        return policyDesc;
    }

    public void setPolicyDesc(String policyDesc) {
        this.policyDesc = policyDesc;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMinAgeDesc() {
        return minAgeDesc;
    }

    public void setMinAgeDesc(String minAgeDesc) {
        this.minAgeDesc = minAgeDesc;
    }

    public String getMaxAgeDesc() {
        return maxAgeDesc;
    }

    public void setMaxAgeDesc(String maxAgeDesc) {
        this.maxAgeDesc = maxAgeDesc;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getRefSourceDesc() {
        return refSourceDesc;
    }

    public void setRefSourceDesc(String refSourceDesc) {
        this.refSourceDesc = refSourceDesc;
    }



}
