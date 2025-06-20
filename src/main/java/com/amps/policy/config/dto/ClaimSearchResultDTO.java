package com.amps.policy.config.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author Anusha Kakarla
 *
 */
public class ClaimSearchResultDTO {

	private Integer policyId;

	private Integer policyNumber;

	private Integer policyVersion;

	private String reasonCode;

	private Integer drgnClaimId;

	private Integer medicalPolicyKeyFk;

	private Integer claimSlId;

	private Integer itemizedBillLineId;

	private String refDrgnClaimId;

	private String submittedChargeAmount;

	private String submitted_modifier_1;

	private String submitted_modifier_2;

	private String submitted_modifier_3;

	private String submitted_modifier_4;

	private String submittedProcedureCode;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private Date dosFrom;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private Date dosTo;

	private String createdDate;

	private Integer drgnChallengeCode;

	private Integer ipuChallengeCode;

	private double drgnChallengeAmt;

	private String ipuChallengeAmt;

	private Integer refipuClaimLineId;

	private String refDrgnClaimSlId;

	private String principalDiags;

	private String diags;

	private String admittingDiags;

	private String externalCauseOfInjuryDiags;

	private String posOrBillType;

	private Integer ipuPatientId;

	private Integer drgnChallengeQty;

	private String clmFormType;

	private String ipuClmType;

	private String ipuClaimRunStatusId;

	private Integer reprocessed;

	private Integer billingProviderId;

	private String clientGroup;

	private String clientCode;

	private String renderingTaxonomy;

	private String lineLevelTaxonomy;

	private String renderingProviderNpi;

	private String taxIdentifier;

	private String dx_code_1;

	private String dx_code_2;

	private String dx_code_3;

	private String dx_code_4;

	private String diagnosisCodes;

	private String conditionCodes;

	private String revenueCode;

	private String submittedUnits;

	private String allowedQuantity;

	private String payerAllowedModifier1;

	private String payerAllowedModifier2;

	private String payerAllowedModifier3;

	private String payerAllowedModifier4;

//	private String renderingProviderTaxonomyCode;

	private String renderingProviderNpiLineLevel;

	private String placeOfService;

	private String payerAllowedUnits;

	private String payerAllowedAmount;

	private String payerAllowedProcedureCode;

	private String socPostalCode;

	private String billingPostalCode;

	private Integer socProviderId;

	private Double rvuPrice;

	private String payerAllowedRevenueCode;

	public String getSocPostalCode() {
		return socPostalCode;
	}

	public void setSocPostalCode(String socPostalCode) {
		this.socPostalCode = socPostalCode;
	}

	public String getBillingPostalCode() {
		return billingPostalCode;
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}

	public Integer getSocProviderId() {
		return socProviderId;
	}

	public void setSocProviderId(Integer socProviderId) {
		this.socProviderId = socProviderId;
	}

	public Double getRvuPrice() {
		return rvuPrice;
	}

	public void setRvuPrice(Double rvuPrice) {
		this.rvuPrice = rvuPrice;
	}

	public String getPayerAllowedProcedureCode() {
		return payerAllowedProcedureCode;
	}

	public void setPayerAllowedProcedureCode(String payerAllowedProcedureCode) {
		this.payerAllowedProcedureCode = payerAllowedProcedureCode;
	}

	public String getPayerAllowedModifier1() {
		return payerAllowedModifier1;
	}

	public void setPayerAllowedModifier1(String payerAllowedModifier1) {
		this.payerAllowedModifier1 = payerAllowedModifier1;
	}

	public String getPayerAllowedModifier2() {
		return payerAllowedModifier2;
	}

	public void setPayerAllowedModifier2(String payerAllowedModifier2) {
		this.payerAllowedModifier2 = payerAllowedModifier2;
	}

	public String getPayerAllowedModifier3() {
		return payerAllowedModifier3;
	}

	public void setPayerAllowedModifier3(String payerAllowedModifier3) {
		this.payerAllowedModifier3 = payerAllowedModifier3;
	}

	public String getPayerAllowedModifier4() {
		return payerAllowedModifier4;
	}

	public void setPayerAllowedModifier4(String payerAllowedModifier4) {
		this.payerAllowedModifier4 = payerAllowedModifier4;
	}

//	public String getRenderingProviderTaxonomyCode() {
//		return renderingProviderTaxonomyCode;
//	}
//
//	public void setRenderingProviderTaxonomyCode(String renderingProviderTaxonomyCode) {
//		this.renderingProviderTaxonomyCode = renderingProviderTaxonomyCode;
//	}

	public String getRenderingProviderNpiLineLevel() {
		return renderingProviderNpiLineLevel;
	}

	public void setRenderingProviderNpiLineLevel(String renderingProviderNpiLineLevel) {
		this.renderingProviderNpiLineLevel = renderingProviderNpiLineLevel;
	}

	public String getPlaceOfService() {
		return placeOfService;
	}

	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
	}

	public String getPayerAllowedUnits() {
		return payerAllowedUnits;
	}

	public void setPayerAllowedUnits(String payerAllowedUnits) {
		this.payerAllowedUnits = payerAllowedUnits;
	}

	public String getAllowedQuantity() {
		return allowedQuantity;
	}

	public void setAllowedQuantity(String allowedQuantity) {
		this.allowedQuantity = allowedQuantity;
	}

	public String getRevenueCode() {
		return revenueCode;
	}

	public void setRevenueCode(String revenueCode) {
		this.revenueCode = revenueCode;
	}

	public String getConditionCodes() {
		return conditionCodes;
	}

	public void setConditionCodes(String conditionCodes) {
		this.conditionCodes = conditionCodes;
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

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public Integer getDrgnClaimId() {
		return drgnClaimId;
	}

	public void setDrgnClaimId(Integer drgnClaimId) {
		this.drgnClaimId = drgnClaimId;
	}

	public Integer getMedicalPolicyKeyFk() {
		return medicalPolicyKeyFk;
	}

	public void setMedicalPolicyKeyFk(Integer medicalPolicyKeyFk) {
		this.medicalPolicyKeyFk = medicalPolicyKeyFk;
	}

	public Integer getClaimSlId() {
		return claimSlId;
	}

	public void setClaimSlId(Integer claimSlId) {
		this.claimSlId = claimSlId;
	}

	public Integer getItemizedBillLineId() {
		return itemizedBillLineId;
	}

	public void setItemizedBillLineId(Integer itemizedBillLineId) {
		this.itemizedBillLineId = itemizedBillLineId;
	}

	public String getSubmittedChargeAmount() {
		return submittedChargeAmount;
	}

	public void setSubmittedChargeAmount(String submittedChargeAmount) {
		this.submittedChargeAmount = submittedChargeAmount;
	}

	public String getSubmitted_modifier_1() {
		return submitted_modifier_1;
	}

	public void setSubmitted_modifier_1(String submitted_modifier_1) {
		this.submitted_modifier_1 = submitted_modifier_1;
	}

	public String getSubmitted_modifier_2() {
		return submitted_modifier_2;
	}

	public void setSubmitted_modifier_2(String submitted_modifier_2) {
		this.submitted_modifier_2 = submitted_modifier_2;
	}

	public String getSubmitted_modifier_3() {
		return submitted_modifier_3;
	}

	public void setSubmitted_modifier_3(String submitted_modifier_3) {
		this.submitted_modifier_3 = submitted_modifier_3;
	}

	public String getSubmitted_modifier_4() {
		return submitted_modifier_4;
	}

	public void setSubmitted_modifier_4(String submitted_modifier_4) {
		this.submitted_modifier_4 = submitted_modifier_4;
	}

	public String getSubmittedProcedureCode() {
		return submittedProcedureCode;
	}

	public void setSubmittedProcedureCode(String submittedProcedureCode) {
		this.submittedProcedureCode = submittedProcedureCode;
	}

	public String getSubmittedUnits() {
		return submittedUnits;
	}

	public void setSubmittedUnits(String submittedUnits) {
		this.submittedUnits = submittedUnits;
	}

	public String getPayerAllowedAmount() {
		return payerAllowedAmount;
	}

	public void setPayerAllowedAmount(String payerAllowedAmount) {
		this.payerAllowedAmount = payerAllowedAmount;
	}

	public Date getDosFrom() {
		return dosFrom;
	}

	public void setDosFrom(Date dosFrom) {
		this.dosFrom = dosFrom;
	}

	public Date getDosTo() {
		return dosTo;
	}

	public void setDosTo(Date dosTo) {
		this.dosTo = dosTo;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDrgnChallengeCode() {
		return drgnChallengeCode;
	}

	public void setDrgnChallengeCode(Integer drgnChallengeCode) {
		this.drgnChallengeCode = drgnChallengeCode;
	}

	public Integer getIpuChallengeCode() {
		return ipuChallengeCode;
	}

	public void setIpuChallengeCode(Integer ipuChallengeCode) {
		this.ipuChallengeCode = ipuChallengeCode;
	}

	public double getDrgnChallengeAmt() {
		return drgnChallengeAmt;
	}

	public void setDrgnChallengeAmt(double drgnChallengeAmt) {
		this.drgnChallengeAmt = drgnChallengeAmt;
	}

	public Integer getRefipuClaimLineId() {
		return refipuClaimLineId;
	}

	public void setRefipuClaimLineId(Integer refipuClaimLineId) {
		this.refipuClaimLineId = refipuClaimLineId;
	}

	public String getRefDrgnClaimSlId() {
		return refDrgnClaimSlId;
	}

	public void setRefDrgnClaimSlId(String refDrgnClaimSlId) {
		this.refDrgnClaimSlId = refDrgnClaimSlId;
	}

	public String getPrincipalDiags() {
		return principalDiags;
	}

	public void setPrincipalDiags(String principalDiags) {
		this.principalDiags = principalDiags;
	}

	public String getDiags() {
		return diags;
	}

	public void setDiags(String diags) {
		this.diags = diags;
	}

	public String getAdmittingDiags() {
		return admittingDiags;
	}

	public void setAdmittingDiags(String admittingDiags) {
		this.admittingDiags = admittingDiags;
	}

	public String getExternalCauseOfInjuryDiags() {
		return externalCauseOfInjuryDiags;
	}

	public void setExternalCauseOfInjuryDiags(String externalCauseOfInjuryDiags) {
		this.externalCauseOfInjuryDiags = externalCauseOfInjuryDiags;
	}

	public String getPosOrBillType() {
		return posOrBillType;
	}

	public void setPosOrBillType(String posOrBillType) {
		this.posOrBillType = posOrBillType;
	}

	public Integer getIpuPatientId() {
		return ipuPatientId;
	}

	public void setIpuPatientId(Integer ipuPatientId) {
		this.ipuPatientId = ipuPatientId;
	}

	public Integer getDrgnChallengeQty() {
		return drgnChallengeQty;
	}

	public void setDrgnChallengeQty(Integer drgnChallengeQty) {
		this.drgnChallengeQty = drgnChallengeQty;
	}

	public String getClmFormType() {
		return clmFormType;
	}

	public void setClmFormType(String clmFormType) {
		this.clmFormType = clmFormType;
	}

	public String getIpuClmType() {
		return ipuClmType;
	}

	public void setIpuClmType(String ipuClmType) {
		this.ipuClmType = ipuClmType;
	}

	public String getIpuClaimRunStatusId() {
		return ipuClaimRunStatusId;
	}

	public void setIpuClaimRunStatusId(String ipuClaimRunStatusId) {
		this.ipuClaimRunStatusId = ipuClaimRunStatusId;
	}

	public Integer getReprocessed() {
		return reprocessed;
	}

	public void setReprocessed(Integer reprocessed) {
		this.reprocessed = reprocessed;
	}

	public Integer getBillingProviderId() {
		return billingProviderId;
	}

	public void setBillingProviderId(Integer billingProviderId) {
		this.billingProviderId = billingProviderId;
	}

	public String getClientGroup() {
		return clientGroup;
	}

	public void setClientGroup(String clientGroup) {
		this.clientGroup = clientGroup;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getRenderingTaxonomy() {
		return renderingTaxonomy;
	}

	public void setRenderingTaxonomy(String renderingTaxonomy) {
		this.renderingTaxonomy = renderingTaxonomy;
	}

	public String getRenderingProviderNpi() {
		return renderingProviderNpi;
	}

	public void setRenderingProviderNpi(String renderingProviderNpi) {
		this.renderingProviderNpi = renderingProviderNpi;
	}

	public String getTaxIdentifier() {
		return taxIdentifier;
	}

	public void setTaxIdentifier(String taxIdentifier) {
		this.taxIdentifier = taxIdentifier;
	}

	public String getDx_code_1() {
		return dx_code_1;
	}

	public void setDx_code_1(String dx_code_1) {
		this.dx_code_1 = dx_code_1;
	}

	public String getDx_code_2() {
		return dx_code_2;
	}

	public void setDx_code_2(String dx_code_2) {
		this.dx_code_2 = dx_code_2;
	}

	public String getDx_code_3() {
		return dx_code_3;
	}

	public void setDx_code_3(String dx_code_3) {
		this.dx_code_3 = dx_code_3;
	}

	public String getDx_code_4() {
		return dx_code_4;
	}

	public void setDx_code_4(String dx_code_4) {
		this.dx_code_4 = dx_code_4;
	}

	public String getDiagnosisCodes() {
		return diagnosisCodes;
	}

	public void setDiagnosisCodes(String diagnosisCodes) {
		this.diagnosisCodes = diagnosisCodes;
	}

	public String getIpuChallengeAmt() {
		return ipuChallengeAmt;
	}

	public void setIpuChallengeAmt(String ipuChallengeAmt) {
		this.ipuChallengeAmt = ipuChallengeAmt;
	}

	public String getRefDrgnClaimId() {
		return refDrgnClaimId;
	}

	public void setRefDrgnClaimId(String string) {
		this.refDrgnClaimId = string;
	}

	public String getLineLevelTaxonomy() {
		return lineLevelTaxonomy;
	}

	public void setLineLevelTaxonomy(String lineLevelTaxonomy) {
		this.lineLevelTaxonomy = lineLevelTaxonomy;
	}

	public String getPayerAllowedRevenueCode() {
		return payerAllowedRevenueCode;
	}

	public void setPayerAllowedRevenueCode(String payerAllowedRevenueCode) {
		this.payerAllowedRevenueCode = payerAllowedRevenueCode;
	}

}
