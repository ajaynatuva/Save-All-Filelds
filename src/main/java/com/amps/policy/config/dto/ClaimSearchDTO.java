package com.amps.policy.config.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClaimSearchDTO {

	private String policyNumber;

	private Integer policyVersion;

	private String reasonCode;

	private String drgnClaimId;

	private Integer claimSlId;

	private String refDrgnClaimIdF;

	private String claimType;

	private Integer medicalPolicyKeyFk;

	private String posOrBillType;

	private String posOrBillTypeF;

	private String clientgroupTypeCode;

	private String clientGroup;

	private String clientCode;

	private String renderingTaxonomy;

	private String lineLevelTaxonomy;

	private String renderingProviderNpi;

	private String taxIdentifier;

	private String processedFrom;

	private String processedTo;

	private Integer startRow;

	private Integer endRow;

	private String drgnClaimIdF;

	private String ipuClaimLineId;

	private String policyNumberF;

	private String policyVersionF;

	private String submittedProcedureCode;

	private String submitted_modifier_1;

	private String submitted_modifier_2;

	private String submitted_modifier_3;

	private String submitted_modifier_4;

	private String dosFrom;

	private String dosTo;

	private String submittedChargeAmount;

	private String drgnChallengeCode;

	private String ipuChallengeCode;

	private String drgnChallengeAmt;

	private String ipuChallengeAmt;

	private String refDrgnClaimSlId;

	private String reasonCodeF;

	private String medicalPolicyF;

	private String conditionCodes;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private String processedOn;

	private boolean isExport;

	private String isSort;

	private String sortColumn;

	private String principalDiags;

	private String principalDiagsF;

	private String diags;

	private String diagsF;

	private String admittingDiags;

	private String admittingDiagsF;

	private String externalCauseOfInjuryDiagsF;

	private String externalCauseOfInjuryDiags;

	private Integer refDragonClaimslId;

	private Integer patientId;

	private Integer drgnChallengeQty;

	private String clmFormType;

	private String ipuClmType;

	private String ipuClaimRunStatusId;

	private Integer reprocessed;

	private Integer billingProviderId;

	private String dx_code_1;

	private String dx_code_2;

	private String dx_code_3;

	private String dx_code_4;

	private String diagnosisCodes;

	private String revenueCode;

	private Integer submittedUnits;

	private Integer allowedQuantity;

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

	private String itemizedBillLineId;

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

	public String getItemizedBillLineId() {
		return itemizedBillLineId;
	}

	public void setItemizedBillLineId(String itemizedBillLineId) {
		this.itemizedBillLineId = itemizedBillLineId;
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

	public Integer getAllowedQuantity() {
		return allowedQuantity;
	}

	public void setAllowedQuantity(Integer allowedQuantity) {
		this.allowedQuantity = allowedQuantity;
	}

	public String getRevenueCode() {
		return revenueCode;
	}

	public void setRevenueCode(String revenueCode) {
		this.revenueCode = revenueCode;
	}

	public String getDiagnosisCodes() {
		return diagnosisCodes;
	}

	public void setDiagnosisCodes(String diagnosisCodes) {
		this.diagnosisCodes = diagnosisCodes;
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

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
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

	public String getDrgnClaimId() {
		return drgnClaimId;
	}

	public void setDrgnClaimId(String drgnClaimId) {
		this.drgnClaimId = drgnClaimId;
	}

	public Integer getClaimSlId() {
		return claimSlId;
	}

	public void setClaimSlId(Integer claimSlId) {
		this.claimSlId = claimSlId;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public Integer getMedicalPolicyKeyFk() {
		return medicalPolicyKeyFk;
	}

	public void setMedicalPolicyKeyFk(Integer medicalPolicyKeyFk) {
		this.medicalPolicyKeyFk = medicalPolicyKeyFk;
	}

	public String getPosOrBillType() {
		return posOrBillType;
	}

	public void setPosOrBillType(String posOrBillType) {
		this.posOrBillType = posOrBillType;
	}

	public String getPosOrBillTypeF() {
		return posOrBillTypeF;
	}

	public void setPosOrBillTypeF(String posOrBillTypeF) {
		this.posOrBillTypeF = posOrBillTypeF;
	}

	public String getClientgroupTypeCode() {
		return clientgroupTypeCode;
	}

	public void setClientgroupTypeCode(String clientgroupTypeCode) {
		this.clientgroupTypeCode = clientgroupTypeCode;
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

	public String getProcessedFrom() {
		return processedFrom;
	}

	public void setProcessedFrom(String processedFrom) {
		this.processedFrom = processedFrom;
	}

	public String getProcessedTo() {
		return processedTo;
	}

	public void setProcessedTo(String processedTo) {
		this.processedTo = processedTo;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	public String getDrgnClaimIdF() {
		return drgnClaimIdF;
	}

	public void setDrgnClaimIdF(String drgnClaimIdF) {
		this.drgnClaimIdF = drgnClaimIdF;
	}

	public String getIpuClaimLineId() {
		return ipuClaimLineId;
	}

	public void setIpuClaimLineId(String ipuClaimLineId) {
		this.ipuClaimLineId = ipuClaimLineId;
	}

	public String getPolicyNumberF() {
		return policyNumberF;
	}

	public void setPolicyNumberF(String policyNumberF) {
		this.policyNumberF = policyNumberF;
	}

	public String getPolicyVersionF() {
		return policyVersionF;
	}

	public void setPolicyVersionF(String policyVersionF) {
		this.policyVersionF = policyVersionF;
	}

	public String getDosFrom() {
		return dosFrom;
	}

	public void setDosFrom(String dosFrom) {
		this.dosFrom = dosFrom;
	}

	public String getDosTo() {
		return dosTo;
	}

	public void setDosTo(String dosTo) {
		this.dosTo = dosTo;
	}

	public Integer getSubmittedUnits() {
		return submittedUnits;
	}

	public void setSubmittedUnits(Integer submittedUnits) {
		this.submittedUnits = submittedUnits;
	}

	public String getPayerAllowedAmount() {
		return payerAllowedAmount;
	}

	public void setPayerAllowedAmount(String payerAllowedAmount) {
		this.payerAllowedAmount = payerAllowedAmount;
	}

	public String getSubmittedProcedureCode() {
		return submittedProcedureCode;
	}

	public void setSubmittedProcedureCode(String submittedProcedureCode) {
		this.submittedProcedureCode = submittedProcedureCode;
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

	public String getSubmittedChargeAmount() {
		return submittedChargeAmount;
	}

	public void setSubmittedChargeAmount(String submittedChargeAmount) {
		this.submittedChargeAmount = submittedChargeAmount;
	}

	public String getDrgnChallengeCode() {
		return drgnChallengeCode;
	}

	public void setDrgnChallengeCode(String drgnChallengeCode) {
		this.drgnChallengeCode = drgnChallengeCode;
	}

	public String getIpuChallengeCode() {
		return ipuChallengeCode;
	}

	public void setIpuChallengeCode(String ipuChallengeCode) {
		this.ipuChallengeCode = ipuChallengeCode;
	}

	public String getDrgnChallengeAmt() {
		return drgnChallengeAmt;
	}

	public void setDrgnChallengeAmt(String drgnChallengeAmt) {
		this.drgnChallengeAmt = drgnChallengeAmt;
	}

	public String getIpuChallengeAmt() {
		return ipuChallengeAmt;
	}

	public void setIpuChallengeAmt(String ipuChallengeAmt) {
		this.ipuChallengeAmt = ipuChallengeAmt;
	}

	public String getRefDrgnClaimSlId() {
		return refDrgnClaimSlId;
	}

	public void setRefDrgnClaimSlId(String refDrgnClaimSlId) {
		this.refDrgnClaimSlId = refDrgnClaimSlId;
	}

	public String getReasonCodeF() {
		return reasonCodeF;
	}

	public void setReasonCodeF(String reasonCodeF) {
		this.reasonCodeF = reasonCodeF;
	}

	public String getMedicalPolicyF() {
		return medicalPolicyF;
	}

	public void setMedicalPolicyF(String medicalPolicyF) {
		this.medicalPolicyF = medicalPolicyF;
	}

	public String getProcessedOn() {
		return processedOn;
	}

	public void setProcessedOn(String processedOn) {
		this.processedOn = processedOn;
	}

	public boolean getIsExport() {
		return isExport;
	}

	public void setIsExport(boolean isExport) {
		this.isExport = isExport;
	}

	public String getIsSort() {
		return isSort;
	}

	public void setIsSort(String isSort) {
		this.isSort = isSort;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getPrincipalDiags() {
		return principalDiags;
	}

	public void setPrincipalDiags(String principalDiags) {
		this.principalDiags = principalDiags;
	}

	public String getPrincipalDiagsF() {
		return principalDiagsF;
	}

	public void setPrincipalDiagsF(String principalDiagsF) {
		this.principalDiagsF = principalDiagsF;
	}

	public String getDiags() {
		return diags;
	}

	public void setDiags(String diags) {
		this.diags = diags;
	}

	public String getDiagsF() {
		return diagsF;
	}

	public void setDiagsF(String diagsF) {
		this.diagsF = diagsF;
	}

	public String getAdmittingDiags() {
		return admittingDiags;
	}

	public void setAdmittingDiags(String admittingDiags) {
		this.admittingDiags = admittingDiags;
	}

	public String getAdmittingDiagsF() {
		return admittingDiagsF;
	}

	public void setAdmittingDiagsF(String admittingDiagsF) {
		this.admittingDiagsF = admittingDiagsF;
	}

	public String getExternalCauseOfInjuryDiagsF() {
		return externalCauseOfInjuryDiagsF;
	}

	public void setExternalCauseOfInjuryDiagsF(String externalCauseOfInjuryDiagsF) {
		this.externalCauseOfInjuryDiagsF = externalCauseOfInjuryDiagsF;
	}

	public String getExternalCauseOfInjuryDiags() {
		return externalCauseOfInjuryDiags;
	}

	public void setExternalCauseOfInjuryDiags(String externalCauseOfInjuryDiags) {
		this.externalCauseOfInjuryDiags = externalCauseOfInjuryDiags;
	}

	public Integer getRefDragonClaimslId() {
		return refDragonClaimslId;
	}

	public void setRefDragonClaimslId(Integer refDragonClaimslId) {
		this.refDragonClaimslId = refDragonClaimslId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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

	public String getrefDrgnClaimIdF() {
		return refDrgnClaimIdF;
	}

	public void setrefDrgnClaimIdF(String refDrgnClaimIdF) {
		this.refDrgnClaimIdF = refDrgnClaimIdF;
	}

	public String getConditionCodes() {
		return conditionCodes;
	}

	public void setConditionCodes(String conditionCodes) {
		this.conditionCodes = conditionCodes;
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
