package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill_type_link_lkp", schema = "policy")
public class PolicyBillTypeLinkLookUp {

	@Id
	@Column(name = "bill_type_link_key")
	private Integer billTypeLinkKey;

	@Column(name = "bill_type_link_desc")
	private String billTypeLinkDesc;

	@Column(name = "bill_type_code")
	private String billTypeCode;

    public PolicyBillTypeLinkLookUp() {
    }

    public Integer getBillTypeLinkKey() {
		return billTypeLinkKey;
	}

	public void setBillTypeLinkKey(Integer billTypeLinkKey) {
		this.billTypeLinkKey = billTypeLinkKey;
	}

	public String getBillTypeLinkDesc() {
		return billTypeLinkDesc;
	}

	public void setBillTypeLinkDesc(String billTypeLinkDesc) {
		this.billTypeLinkDesc = billTypeLinkDesc;
	}

	public String getBillTypeCode() {
		return billTypeCode;
	}

	public void setBillTypeCode(String billTypeCode) {
		this.billTypeCode = billTypeCode;
	}

}
