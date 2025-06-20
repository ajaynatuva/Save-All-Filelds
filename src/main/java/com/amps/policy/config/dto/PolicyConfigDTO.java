package com.amps.policy.config.dto;

import com.amps.policy.config.model.ClientAssignment;
import com.amps.policy.config.model.Taxonomy;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PolicyConfigDTO {

    private Integer policyId;

    private Integer policyNumber;

    private Integer policyVersion;

    private Integer custom;

    private Integer clonedPolicyId;

    private String policyDesc;

    private Integer deactivated;

    private Integer disabled;

    private String claimType;

    private Integer medicalPolicyKey;

    private Integer subPolicyKey;

    private String reference;

    private Integer policyCategoryLkpId;

    private Integer priority;

    private Integer enforceBeforeCategory;

    private Integer genderCode;

    private Integer minAgeFk;

    private Integer maxAgeFk;

    private Integer npiLogicFk;

    private Integer taxLogicFk;

    private Integer taxonomyLogicFk;

    private String reasonCodeFk;

    private Integer isProdb;

    private String notes;

    private String summary;

    private String explanation;

    private String refSourceDesc;

    private String refSourceTitle;

    private String sourceIndicator;

    private Integer lobFk;

    private String productTypeFk;

    private Date createdDate;

    private String createdBy;

    private String updatedBy;

    private Date updatedOn;

    private String co1Value;

    private String co2Value;

    private String co3Value;

    private String co4Value;

    private String co5Value;

    private Integer ncciB;

    private Integer group59B;

    private Integer billTypeLinkFk;

    private Integer billTypeActionFk;

    private Integer posLinkFk;

    private Integer conditionCodeActionFk;

    private Integer revenueCodeClaimLinkFk;

    private Integer claimTypeLinkFk;

    private Integer asGroupB;

    private Integer tc26ModB;

    private Integer cptLinkFk;

    private String clientCreatedDate;

    private Integer referenceClaimType;
    
    private Integer referZeroChargeLine;

    private Integer forClientTabPolicyId;

    private boolean addAllActiveClients;

    private List<Taxonomy>taxonomyList;


    public Integer getForClientTabPolicyId() {
        return forClientTabPolicyId;
    }

    public void setForClientTabPolicyId(Integer forClientTabPolicyId) {
        this.forClientTabPolicyId = forClientTabPolicyId;
    }

    public Integer getCptLinkFk() {
        return cptLinkFk;
    }

    public void setCptLinkFk(Integer cptLinkFk) {
        this.cptLinkFk = cptLinkFk;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Integer policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Integer getPolicyVersion() {
        return policyVersion;
    }

    public void setPolicyVersion(Integer policyVersion) {
        this.policyVersion = policyVersion;
    }

    public String getPolicyDesc() {
        return policyDesc;
    }

    public void setPolicyDesc(String policyDesc) {
        this.policyDesc = policyDesc;
    }

    public Integer getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Integer deactivated) {
        this.deactivated = deactivated;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public Integer getMedicalPolicyKey() {
        return medicalPolicyKey;
    }

    public void setMedicalPolicyKey(Integer medicalPolicyKey) {
        this.medicalPolicyKey = medicalPolicyKey;
    }

    public Integer getSubPolicyKey() {
        return subPolicyKey;
    }

    public void setSubPolicyKey(Integer subPolicyKey) {
        this.subPolicyKey = subPolicyKey;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getPolicyCategoryLkpId() {
        return policyCategoryLkpId;
    }

    public void setPolicyCategoryLkpId(Integer policyCategoryLkpId) {
        this.policyCategoryLkpId = policyCategoryLkpId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getEnforceBeforeCategory() {
        return enforceBeforeCategory;
    }

    public void setEnforceBeforeCategory(Integer enforceBeforeCategory) {
        this.enforceBeforeCategory = enforceBeforeCategory;
    }

    public Integer getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(Integer genderCode) {
        this.genderCode = genderCode;
    }

    public Integer getMinAgeFk() {
        return minAgeFk;
    }

    public void setMinAgeFk(Integer minAgeFk) {
        this.minAgeFk = minAgeFk;
    }

    public Integer getMaxAgeFk() {
        return maxAgeFk;
    }

    public void setMaxAgeFk(Integer maxAgeFk) {
        this.maxAgeFk = maxAgeFk;
    }

    public Integer getNpiLogicFk() {
        return npiLogicFk;
    }

    public void setNpiLogicFk(Integer npiLogicFk) {
        this.npiLogicFk = npiLogicFk;
    }

    public Integer getTaxLogicFk() {
        return taxLogicFk;
    }

    public void setTaxLogicFk(Integer taxLogicFk) {
        this.taxLogicFk = taxLogicFk;
    }

    public Integer getTaxonomyLogicFk() {
        return taxonomyLogicFk;
    }

    public void setTaxonomyLogicFk(Integer taxonomyLogicFk) {
        this.taxonomyLogicFk = taxonomyLogicFk;
    }

    public String getReasonCodeFk() {
        return reasonCodeFk;
    }

    public void setReasonCodeFk(String reasonCodeFk) {
        this.reasonCodeFk = reasonCodeFk;
    }

    public Integer getIsProdb() {
        return isProdb;
    }

    public void setIsProdb(Integer isProdb) {
        this.isProdb = isProdb;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRefSourceDesc() {
        return refSourceDesc;
    }

    public void setRefSourceDesc(String refSourceDesc) {
        this.refSourceDesc = refSourceDesc;
    }

    public String getRefSourceTitle() {
        return refSourceTitle;
    }

    public void setRefSourceTitle(String refSourceTitle) {
        this.refSourceTitle = refSourceTitle;
    }

    public String getSourceIndicator() {
        return sourceIndicator;
    }

    public void setSourceIndicator(String sourceIndicator) {
        this.sourceIndicator = sourceIndicator;
    }

    public Integer getLobFk() {
        return lobFk;
    }

    public void setLobFk(Integer lobFk) {
        this.lobFk = lobFk;
    }

    public String getProductTypeFk() {
        return productTypeFk;
    }

    public void setProductTypeFk(String productTypeFk) {
        this.productTypeFk = productTypeFk;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getCo1Value() {
        return co1Value;
    }

    public void setCo1Value(String co1Value) {
        this.co1Value = co1Value;
    }

    public String getCo2Value() {
        return co2Value;
    }

    public void setCo2Value(String co2Value) {
        this.co2Value = co2Value;
    }

    public String getCo3Value() {
        return co3Value;
    }

    public void setCo3Value(String co3Value) {
        this.co3Value = co3Value;
    }

    public String getCo4Value() {
        return co4Value;
    }

    public void setCo4Value(String co4Value) {
        this.co4Value = co4Value;
    }

    public String getCo5Value() {
        return co5Value;
    }

    public void setCo5Value(String co5Value) {
        this.co5Value = co5Value;
    }

    public Integer getNcciB() {
        return ncciB;
    }

    public void setNcciB(Integer ncciB) {
        this.ncciB = ncciB;
    }

    public Integer getGroup59B() {
        return group59B;
    }

    public void setGroup59B(Integer group59b) {
        group59B = group59b;
    }

    public Integer getBillTypeLinkFk() {
        return billTypeLinkFk;
    }

    public void setBillTypeLinkFk(Integer billTypeLinkFk) {
        this.billTypeLinkFk = billTypeLinkFk;
    }

    public Integer getBillTypeActionFk() {
        return billTypeActionFk;
    }

    public void setBillTypeActionFk(Integer billTypeActionFk) {
        this.billTypeActionFk = billTypeActionFk;
    }

    public Integer getPosLinkFk() {
        return posLinkFk;
    }

    public void setPosLinkFk(Integer posLinkFk) {
        this.posLinkFk = posLinkFk;
    }

    public Integer getConditionCodeActionFk() {
        return conditionCodeActionFk;
    }

    public void setConditionCodeActionFk(Integer conditionCodeActionFk) {
        this.conditionCodeActionFk = conditionCodeActionFk;
    }

    public Integer getRevenueCodeClaimLinkFk() {
        return revenueCodeClaimLinkFk;
    }

    public void setRevenueCodeClaimLinkFk(Integer revenueCodeClaimLinkFk) {
        this.revenueCodeClaimLinkFk = revenueCodeClaimLinkFk;
    }

    public Integer getClaimTypeLinkFk() {
        return claimTypeLinkFk;
    }

    public void setClaimTypeLinkFk(Integer claimTypeLinkFk) {
        this.claimTypeLinkFk = claimTypeLinkFk;
    }

    public Integer getAsGroupB() {
        return asGroupB;
    }

    public void setAsGroupB(Integer asGroupB) {
        this.asGroupB = asGroupB;
    }

    public Integer getTc26ModB() {
        return tc26ModB;
    }

    public void setTc26ModB(Integer tc26ModB) {
        this.tc26ModB = tc26ModB;
    }

    public Integer getCustom() {
        return custom;
    }

    public void setCustom(Integer custom) {
        this.custom = custom;
    }

    public Integer getClonedPolicyId() {
        return clonedPolicyId;
    }

    public void setClonedPolicyId(Integer clonedPolicyId) {
        this.clonedPolicyId = clonedPolicyId;
    }

    public boolean isAddAllActiveClients() {
        return addAllActiveClients;
    }

    public void setAddAllActiveClients(boolean addAllActiveClients) {
        this.addAllActiveClients = addAllActiveClients;
    }

    public String getClientCreatedDate() {
        return clientCreatedDate;
    }

    public void setClientCreatedDate(String clientCreatedDate) {
        this.clientCreatedDate = clientCreatedDate;
    }

    public Integer getReferenceClaimType() {
        return referenceClaimType;
    }

    public void setReferenceClaimType(Integer referenceClaimType) {
        this.referenceClaimType = referenceClaimType;
    }

	public Integer getReferZeroChargeLine() {
		return referZeroChargeLine;
	}

	public void setReferZeroChargeLine(Integer referZeroChargeLine) {
		this.referZeroChargeLine = referZeroChargeLine;
	}

    public List<Taxonomy> getTaxonomyList() {
        return taxonomyList;
    }

    public void setTaxonomyList(List<Taxonomy> taxonomyList) {
        this.taxonomyList = taxonomyList;
    }
}
