package com.amps.policy.config.dto;

public class PolicySearchDTO {

	private String policyNumber;

	private Boolean custom;

	private Integer version;

	private String category;

	private String reason;

	private Integer policyId;

	private Integer lob;

	private String productType;

	private String ebc;

	private String claimType;

	private String medicalPolicy;

	private String subPolicy;

	private String reference;

	private Integer priority;

	private String description;

	private String createDate;

	private Integer deactivated;

	private Integer disabled;

	private String cptCode;

	private String pos;

	private String billType;
	
	private Integer clientGroup;

	public String getPolicyNumber() {
		return policyNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public Integer getLob() {
		return lob;
	}

	public void setLob(Integer lob) {
		this.lob = lob;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getEbc() {
		return ebc;
	}

	public void setEbc(String ebc) {
		this.ebc = ebc;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	public String getCptCode() {
		return cptCode;
	}

	public void setCptCode(String cptCode) {
		this.cptCode = cptCode;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Boolean getCustom() {
		return custom;
	}

	public void setCustom(Boolean custom) {
		this.custom = custom;
	}
	
	public Integer getClientGroup() {
		return clientGroup;
	}

	public void setClientGroup(Integer clientGroup) {
		this.clientGroup = clientGroup;
	}

	@Override
	public String toString() {
		return "PolicySearchDTO [policyNumber=" + policyNumber + ", custom=" + custom + ", version=" + version
				+ ", category=" + category + ", reason=" + reason + ", policyId=" + policyId + ", lob=" + lob
				+ ", productType=" + productType + ", ebc=" + ebc + ", claimType=" + claimType + ", medicalPolicy="
				+ medicalPolicy + ", subPolicy=" + subPolicy + ", reference=" + reference + ", priority=" + priority
				+ ", description=" + description + ", createDate=" + createDate + ", deactivated=" + deactivated
				+ ", disabled=" + disabled + ", cptCode=" + cptCode + ", pos=" + pos + ", billType=" + billType
				+ ", clientGroup=" + clientGroup + "]";
	}
	
	public PolicySearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
