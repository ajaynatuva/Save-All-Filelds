package com.amps.policy.config.model;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "policy", schema = "policy")
public class Policy {
    @Id
    @SequenceGenerator(name = "policy.policy_policy_id_seq", sequenceName = "policy.policy_policy_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.policy_policy_id_seq")
    @Column(name = "policy_id", updatable = false)
    private Integer policyId;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.policy_policy_number_seq")
    @SequenceGenerator(name = "policy.policy_policy_number_seq", sequenceName = "policy.policy_policy_number_seq", allocationSize = 1)
    @Column(name = "policy_number", updatable = false)
    private Integer policyNumber;

    @Column(name = "policy_version", updatable = false)
    private Integer policyVersion;

    @Column(name = "policy_desc")
    private String policyDesc;

    @Column(name = "deactivated")
    private Integer deactivated;

    @Column(name = "disabled")
    private Integer disabled;

    @Column(name = "claim_type")
    private String claimType;

    @ManyToOne
    @JoinColumn(name = "medical_policy_key_fk")
    private MedicalPolicyLookup medicalPolicyKeyFK;

    @ManyToOne
    @JoinColumn(name = "sub_policy_key_fk")
    private SubPolicies subPolicyKeyFK;

    @Column(name = "reference")
    private String reference;

    @ManyToOne
    @JoinColumn(name = "category_fk")
    private PolicyCategoryLookUp policyCategoryLkpIdFk;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "enforce_before_category")
    private Integer enforceBeforeCategory;

    @ManyToOne
    @JoinColumn(name = "gender_fk")
    private GenderLookUp genderCode;

    @ManyToOne
    @JoinColumn(name = "min_age_fk")
    private MinMaxAgeLookUp minAgeFk;

    @ManyToOne
    @JoinColumn(name = "max_age_fk")
    private MinMaxAgeLookUp maxAgeFk;

    @ManyToOne
    @JoinColumn(name = "npi_logic_fk")
    private NpiLinkLookUp npiLogicFk;

    @ManyToOne
    @JoinColumn(name = "tax_logic_fk")
    private TaxLinkLookUp taxLogicFk;

    @ManyToOne
    @JoinColumn(name = "taxonomy_logic_fk")
    private TaxonomyLinkLookUp taxonomyLogicFk;

    @ManyToOne
    @JoinColumn(name = "reason_code_fk")
    private ReasonCodeLookUp reasonCodeFk;

    @Column(name = "is_prod_b")
    private Integer isProdb;

    @Column(name = "notes")
    private String notes;

    @Column(name = "summary")
    private String summary;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "ref_source_desc")
    private String refSourceDesc;

    @Column(name = "ref_source_title")
    private String refSourceTitle;

    @Column(name = "source_indicator")
    private String sourceIndicator;

    @ManyToOne
    @JoinColumn(name = "lob_fk")
    private LobLookUp lobFk;

    //	@ManyToOne
//	@JoinColumn(name = "product_type_fk")
    @Column(name = "product_type_fk")
    private String productTypeFk;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    @CreationTimestamp
    private Date updatedOn;

    @Column(name = "co1_value")
    private String co1Value;

    @Column(name = "co2_value")
    private String co2Value;

    @Column(name = "co3_value")
    private String co3Value;

    @Column(name = "co4_value")
    private String co4Value;

    @Column(name = "co5_value")
    private String co5Value;

    @Column(name = "ncci_b")
    private Integer ncciB;

    @Column(name = "group_59_b")
    private Integer group59B;

    @ManyToOne
    @JoinColumn(name = "bill_type_link_fk")
    private PolicyBillTypeLinkLookUp billTypeLinkFk;

    @ManyToOne
    @JoinColumn(name = "bill_type_action_fk")
    private PolicyActionBillTypeLookUp billTypeActionFk;

    @ManyToOne
    @JoinColumn(name = "pos_link_fk")
    private PosLinkLookUp posLinkFk;

    @ManyToOne
    @JoinColumn(name = "condition_code_action_fk")
    private ConditionCodeActionLkp conditionCodeActionFk;

    @ManyToOne
    @JoinColumn(name = "revenue_code_claim_link_fk")
    private RevenueCodeClaimLink revenueCodeClaimLinkFk;

    @ManyToOne
    @JoinColumn(name = "claim_type_link_fk")
    private ClaimTypeLinkLkp claimTypeLinkFk;

    @Column(name = "as_group_b")
    private Integer asGroupB;

    @Column(name = "tc_26_mod_b")
    private Integer tc26ModB;

    @ManyToOne
    @JoinColumn(name = "cpt_link_fk")
    private CptLinkLookUp cptLinkFk;

    @Column(name = "custom")
    private boolean custom;

    @Column(name = "cloned_policy_id")
    private Integer clonedPolicyId;

    @Transient
    private String clonedPolVer;

    @Column(name = "reference_claim_type")
    private Integer referenceClaimType;
    
    @Column(name = "refer_zero_charge_line")
    private Integer referZeroChargeLine;

    public String getClonedPolVer() {
        return clonedPolVer;
    }

    public void setClonedPolVer(String clonedPolVer) {
        this.clonedPolVer = clonedPolVer;
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

    public MedicalPolicyLookup getMedicalPolicyKeyFK() {
        return medicalPolicyKeyFK;
    }

    public void setMedicalPolicyKeyFK(MedicalPolicyLookup medicalPolicyKeyFK) {
        this.medicalPolicyKeyFK = medicalPolicyKeyFK;
    }

    public SubPolicies getSubPolicyKeyFK() {
        return subPolicyKeyFK;
    }

    public void setSubPolicyKeyFK(SubPolicies subPolicyKeyFK) {
        this.subPolicyKeyFK = subPolicyKeyFK;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public PolicyCategoryLookUp getPolicyCategoryLkpIdFk() {
        return policyCategoryLkpIdFk;
    }

    public void setPolicyCategoryLkpIdFk(PolicyCategoryLookUp policyCategoryLkpIdFk) {
        this.policyCategoryLkpIdFk = policyCategoryLkpIdFk;
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

    public GenderLookUp getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(GenderLookUp genderCode) {
        this.genderCode = genderCode;
    }

    public MinMaxAgeLookUp getMinAgeFk() {
        return minAgeFk;
    }

    public void setMinAgeFk(MinMaxAgeLookUp minAgeFk) {
        this.minAgeFk = minAgeFk;
    }

    public MinMaxAgeLookUp getMaxAgeFk() {
        return maxAgeFk;
    }

    public void setMaxAgeFk(MinMaxAgeLookUp maxAgeFk) {
        this.maxAgeFk = maxAgeFk;
    }

    public NpiLinkLookUp getNpiLogicFk() {
        return npiLogicFk;
    }

    public void setNpiLogicFk(NpiLinkLookUp npiLogicFk) {
        this.npiLogicFk = npiLogicFk;
    }

    public TaxLinkLookUp getTaxLogicFk() {
        return taxLogicFk;
    }

    public void setTaxLogicFk(TaxLinkLookUp taxLogicFk) {
        this.taxLogicFk = taxLogicFk;
    }

    public TaxonomyLinkLookUp getTaxonomyLogicFk() {
        return taxonomyLogicFk;
    }

    public void setTaxonomyLogicFk(TaxonomyLinkLookUp taxonomyLogicFk) {
        this.taxonomyLogicFk = taxonomyLogicFk;
    }

    public ReasonCodeLookUp getReasonCodeFk() {
        return reasonCodeFk;
    }

    public void setReasonCodeFk(ReasonCodeLookUp reasonCodeFk) {
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

    public LobLookUp getLobFk() {
        return lobFk;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setLobFk(LobLookUp lobFk) {
        this.lobFk = lobFk;
    }

//	public ProductTypeLookUp getProductTypeFk() {
//		return productTypeFk;
//	}
//
//	public void setProductTypeFk(ProductTypeLookUp productTypeFk) {
//		this.productTypeFk = productTypeFk;
//	}

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

    public PolicyBillTypeLinkLookUp getBillTypeLinkFk() {
        return billTypeLinkFk;
    }

    public void setBillTypeLinkFk(PolicyBillTypeLinkLookUp billTypeLinkFk) {
        this.billTypeLinkFk = billTypeLinkFk;
    }

    public PolicyActionBillTypeLookUp getBillTypeActionFk() {
        return billTypeActionFk;
    }

    public void setBillTypeActionFk(PolicyActionBillTypeLookUp billTypeActionFk) {
        this.billTypeActionFk = billTypeActionFk;
    }

    public PosLinkLookUp getPosLinkFk() {
        return posLinkFk;
    }

    public void setPosLinkFk(PosLinkLookUp posLinkFk) {
        this.posLinkFk = posLinkFk;
    }

    public ConditionCodeActionLkp getConditionCodeActionFk() {
        return conditionCodeActionFk;
    }

    public void setConditionCodeActionFk(ConditionCodeActionLkp conditionCodeActionFk) {
        this.conditionCodeActionFk = conditionCodeActionFk;
    }

    public RevenueCodeClaimLink getRevenueCodeClaimLinkFk() {
        return revenueCodeClaimLinkFk;
    }

    public void setRevenueCodeClaimLinkFk(RevenueCodeClaimLink revenueCodeClaimLinkFk) {
        this.revenueCodeClaimLinkFk = revenueCodeClaimLinkFk;
    }

    public ClaimTypeLinkLkp getClaimTypeLinkFk() {
        return claimTypeLinkFk;
    }

    public void setClaimTypeLinkFk(ClaimTypeLinkLkp claimTypeLinkFk) {
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

    public CptLinkLookUp getCptLinkFk() {
        return cptLinkFk;
    }

    public void setCptLinkFk(CptLinkLookUp cptLinkFk) {
        this.cptLinkFk = cptLinkFk;
    }

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public Integer getClonedPolicyId() {
        return clonedPolicyId;
    }

    public void setClonedPolicyId(Integer clonedPolicyId) {
        this.clonedPolicyId = clonedPolicyId;
    }

    public Integer getReferenceClaimType() {
        return referenceClaimType;
    }

    public void setReferenceClaimType(Integer referenceClaimType) {
        this.referenceClaimType = referenceClaimType;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
    
    public Integer getReferZeroChargeLine() {
		return referZeroChargeLine;
	}

	public void setReferZeroChargeLine(Integer referZeroChargeLine) {
		this.referZeroChargeLine = referZeroChargeLine;
	}

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyNumber=" + policyNumber + ", policyVersion=" + policyVersion
				+ ", policyDesc=" + policyDesc + ", deactivated=" + deactivated + ", disabled=" + disabled
				+ ", claimType=" + claimType + ", medicalPolicyKeyFK=" + medicalPolicyKeyFK + ", subPolicyKeyFK="
				+ subPolicyKeyFK + ", reference=" + reference + ", policyCategoryLkpIdFk=" + policyCategoryLkpIdFk
				+ ", priority=" + priority + ", enforceBeforeCategory=" + enforceBeforeCategory + ", genderCode="
				+ genderCode + ", minAgeFk=" + minAgeFk + ", maxAgeFk=" + maxAgeFk + ", npiLogicFk=" + npiLogicFk
				+ ", taxLogicFk=" + taxLogicFk + ", taxonomyLogicFk=" + taxonomyLogicFk + ", reasonCodeFk="
				+ reasonCodeFk + ", isProdb=" + isProdb + ", notes=" + notes + ", summary=" + summary + ", explanation="
				+ explanation + ", refSourceDesc=" + refSourceDesc + ", refSourceTitle=" + refSourceTitle
				+ ", sourceIndicator=" + sourceIndicator + ", lobFk=" + lobFk + ", productTypeFk=" + productTypeFk
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", updatedOn=" + updatedOn + ", co1Value=" + co1Value + ", co2Value=" + co2Value + ", co3Value="
				+ co3Value + ", co4Value=" + co4Value + ", co5Value=" + co5Value + ", ncciB=" + ncciB + ", group59B="
				+ group59B + ", billTypeLinkFk=" + billTypeLinkFk + ", billTypeActionFk=" + billTypeActionFk
				+ ", posLinkFk=" + posLinkFk + ", conditionCodeActionFk=" + conditionCodeActionFk
				+ ", revenueCodeClaimLinkFk=" + revenueCodeClaimLinkFk + ", claimTypeLinkFk=" + claimTypeLinkFk
				+ ", asGroupB=" + asGroupB + ", tc26ModB=" + tc26ModB + ", cptLinkFk=" + cptLinkFk + ", custom="
				+ custom + ", clonedPolicyId=" + clonedPolicyId + ", clonedPolVer=" + clonedPolVer
				+ ", referenceClaimType=" + referenceClaimType + ", referZeroChargeLine=" + referZeroChargeLine + "]";
	}

	public Policy() {

    }
}