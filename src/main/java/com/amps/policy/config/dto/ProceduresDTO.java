package com.amps.policy.config.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;

import java.util.Date;

public class ProceduresDTO {

	private Integer policyId;
	private String cptFrom;
	private String cptTo;
	private String mod1;
	private String mod2;
	private String mod3;
	private Integer daysLo;
	private Integer daysHi;
	private String revFrom;
	private String revTo;
	private String pos;
	private String policyCptActionCode;
	private String claimLinkCode;
	private String claimLinkDesc;
	private Integer policyCptActionKey;
	private Integer excludeb;
	private Integer dxLink;
	private Integer claimLinkKey;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private Date dosFrom;

	@JsonFormat(pattern = "MM-dd-yyyy")
	private Date dosTo;

	public String getPolicyCptActionCode() {
		return policyCptActionCode;
	}

	public void setPolicyCptActionCode(String policyCptActionCode) {
		this.policyCptActionCode = policyCptActionCode;
	}

	public String getClaimLinkCode() {
		return claimLinkCode;
	}

	public void setClaimLinkCode(String claimLinkCode) {
		this.claimLinkCode = claimLinkCode;
	}

	public String getClaimLinkDesc() {
		return claimLinkDesc;
	}

	public void setClaimLinkDesc(String claimLinkDesc) {
		this.claimLinkDesc = claimLinkDesc;
	}

	public Integer getClaimLinkKey() {
		return claimLinkKey;
	}

	public void setClaimLinkKey(Integer claimLinkKey) {
		this.claimLinkKey = claimLinkKey;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getCptFrom() {
		return cptFrom;
	}

	public void setCptFrom(String cptFrom) {
		this.cptFrom = cptFrom;
	}

	public String getCptTo() {
		return cptTo;
	}

	public void setCptTo(String cptTo) {
		this.cptTo = cptTo;
	}

	public String getMod1() {
		return mod1;
	}

	public void setMod1(String mod1) {
		this.mod1 = mod1;
	}

	public String getMod2() {
		return mod2;
	}

	public void setMod2(String mod2) {
		this.mod2 = mod2;
	}

	public String getMod3() {
		return mod3;
	}

	public void setMod3(String mod3) {
		this.mod3 = mod3;
	}

	public Integer getDaysLo() {
		return daysLo;
	}

	public void setDaysLo(Integer daysLo) {
		this.daysLo = daysLo;
	}

	public Integer getDaysHi() {
		return daysHi;
	}

	public void setDaysHi(Integer daysHi) {
		this.daysHi = daysHi;
	}

	public String getRevFrom() {
		return revFrom;
	}

	public void setRevFrom(String revFrom) {
		this.revFrom = revFrom;
	}

	public String getRevTo() {
		return revTo;
	}

	public void setRevTo(String revTo) {
		this.revTo = revTo;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public Integer getPolicyCptActionKey() {
		return policyCptActionKey;
	}

	public void setPolicyCptActionKey(Integer policyCptActionKey) {
		this.policyCptActionKey = policyCptActionKey;
	}

	public Integer getExcludeb() {
		return excludeb;
	}

	public void setExcludeb(Integer excludeb) {
		this.excludeb = excludeb;
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

	public Integer getDxLink() {
		return dxLink;
	}

	public void setDxLink(Integer dxLink) {
		this.dxLink = dxLink;
	}
}
