package com.amps.policy.config.model;

import java.util.Date;

public class Claim {
private Integer policyId;
	
	private  Integer policyNumber;
	
	private Integer policyVersion;
	
	private ReasonCodeLookUp reasonCode;
	
	private Integer drgnClaimId;
	
	private Integer ipuClaimLineId;
	
	private String claimType;
	
	private MedicalPolicyLookup medicalPolicyKeyFk;
	
	private String posOrBillType;
	
	private String clientgroupTypeCode;
	
	private String clmFormType;
	
	private Integer clientGroupId;
	
	private Integer claimSlId;
	
	private Integer totalChargeAmount;
	
	private String modifier_1;
	private String modifier_2;
	private String modifier_3;
	private String modifier_4;
	
	private String procedureCode;
	
	private Date dosFrom;
	
	private Date dosTo;

	private Integer drgnChallengeCode;
	
	private Integer ipuChallengeCode;
	
	private double drgnChallengeAmt;
	
	private double ipuChallengeAmt;
	
	private Integer refipuClaimLineId;

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

	

	public ReasonCodeLookUp getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(ReasonCodeLookUp reasonCode) {
		this.reasonCode = reasonCode;
	}

	public Integer getDrgnClaimId() {
		return drgnClaimId;
	}

	public void setDrgnClaimId(Integer drgnClaimId) {
		this.drgnClaimId = drgnClaimId;
	}

	public Integer getIpuClaimLineId() {
		return ipuClaimLineId;
	}

	public void setIpuClaimLineId(Integer ipuClaimLineId) {
		this.ipuClaimLineId = ipuClaimLineId;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	

	public MedicalPolicyLookup getMedicalPolicyKeyFk() {
		return medicalPolicyKeyFk;
	}

	public void setMedicalPolicyKeyFk(MedicalPolicyLookup medicalPolicyKeyFk) {
		this.medicalPolicyKeyFk = medicalPolicyKeyFk;
	}

	public String getPosOrBillType() {
		return posOrBillType;
	}

	public void setPosOrBillType(String posOrBillType) {
		this.posOrBillType = posOrBillType;
	}

	public String getClientgroupTypeCode() {
		return clientgroupTypeCode;
	}

	public void setClientgroupTypeCode(String clientgroupTypeCode) {
		this.clientgroupTypeCode = clientgroupTypeCode;
	}

	public String getClmFormType() {
		return clmFormType;
	}

	public void setClmFormType(String clmFormType) {
		this.clmFormType = clmFormType;
	}

	public Integer getClientGroupId() {
		return clientGroupId;
	}

	public void setClientGroupId(Integer clientGroupId) {
		this.clientGroupId = clientGroupId;
	}

	public Integer getClaimSlId() {
		return claimSlId;
	}

	public void setClaimSlId(Integer claimSlId) {
		this.claimSlId = claimSlId;
	}

	public Integer getTotalChargeAmount() {
		return totalChargeAmount;
	}

	public void setTotalChargeAmount(Integer totalChargeAmount) {
		this.totalChargeAmount = totalChargeAmount;
	}

	public String getModifier_1() {
		return modifier_1;
	}

	public void setModifier_1(String modifier_1) {
		this.modifier_1 = modifier_1;
	}

	public String getModifier_2() {
		return modifier_2;
	}

	public void setModifier_2(String modifier_2) {
		this.modifier_2 = modifier_2;
	}

	public String getModifier_3() {
		return modifier_3;
	}

	public void setModifier_3(String modifier_3) {
		this.modifier_3 = modifier_3;
	}

	public String getModifier_4() {
		return modifier_4;
	}

	public void setModifier_4(String modifier_4) {
		this.modifier_4 = modifier_4;
	}

	public String getProcedureCode() {
		return procedureCode;
	}

	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
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

	public double getIpuChallengeAmt() {
		return ipuChallengeAmt;
	}

	public void setIpuChallengeAmt(double ipuChallengeAmt) {
		this.ipuChallengeAmt = ipuChallengeAmt;
	}

	public Integer getRefipuClaimLineId() {
		return refipuClaimLineId;
	}

	public void setRefipuClaimLineId(Integer refipuClaimLineId) {
		this.refipuClaimLineId = refipuClaimLineId;
	}

	@Override
	public String toString() {
		return "ClaimSearchResultDTO [policyId=" + policyId + ", policyNumber=" + policyNumber + ", policyVersion="
				+ policyVersion + ", reasonCode=" + reasonCode + ", drgnClaimId=" + drgnClaimId + ", ipuClaimLineId="
				+ ipuClaimLineId + ", claimType=" + claimType + ", medicalPolicyKeyFk=" + medicalPolicyKeyFk
				+ ", posOrBillType=" + posOrBillType + ", clientgroupTypeCode=" + clientgroupTypeCode + ", clmFormType="
				+ clmFormType + ", clientGroupId=" + clientGroupId + ", claimSlId=" + claimSlId + ", totalChargeAmount="
				+ totalChargeAmount + ", modifier_1=" + modifier_1 + ", modifier_2=" + modifier_2 + ", modifier_3="
				+ modifier_3 + ", modifier_4=" + modifier_4 + ", procedureCode=" + procedureCode + ", dosFrom="
				+ dosFrom + ", dosTo=" + dosTo + ", drgnChallengeCode=" + drgnChallengeCode + ", ipuChallengeCode="
				+ ipuChallengeCode + ", drgnChallengeAmt=" + drgnChallengeAmt + ", ipuChallengeAmt=" + ipuChallengeAmt
				+ ", refipuClaimLineId=" + refipuClaimLineId + "]";
	}
	
}
