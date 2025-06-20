package com.amps.policy.config.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "policy_procedures", schema = "policy")
public class Procedures {

	@Id
	@SequenceGenerator(name = "policy.policy_procedures_policy_id_seq", sequenceName = "policy.policy_procedures_policy_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.policy_procedures_policy_id_seq")
	@Column(name = "policy_procedures_key", updatable = false)
	private Integer policyProceduresKey;

	@ManyToOne
	@JoinColumn(name = "policy_id")
	private Policy policyId;

	@Column(name = "cpt_from")
	private String cptFrom;

	@Column(name = "cpt_to")
	private String cptTo;

	@Column(name = "mod1")
	private String mod1;

	@Column(name = "mod2")
	private String mod2;

	@Column(name = "mod3")
	private String mod3;

	@Column(name = "days_lo")
	private Integer daysLo;

	@Column(name = "days_hi")
	private Integer daysHi;

	@Column(name = "rev_from")
	private String revFrom;

	@Column(name = "rev_to")
	private String revTo;

	@Column(name = "pos")
	private String pos;

	@Column(name = "dos_from")
	@JsonFormat(pattern = "MM-dd-yyyy")
	private String dosFrom;

	@Column(name = "dos_to")
	@JsonFormat(pattern = "MM-dd-yyyy")
	private String dosTo;

	@ManyToOne
	@JoinColumn(name = "action_fk")
	private PolicyActionCptLookUp policyAction;

	@Column(name = "exclude_b")
	private Integer excludeb;

	@Column(name = "dx_link")
	private Integer dxLink;
	
	@ManyToOne
	@JoinColumn(name = "clm_link_fk")
	private ClaimLinkLookUp clm_link;
	
	public ClaimLinkLookUp getClm_link() {
		return clm_link;
	}

	public void setClm_link(ClaimLinkLookUp clm_link) {
		this.clm_link = clm_link;
	}

	public Integer getPolicyProceduresKey() {
		return policyProceduresKey;
	}

	public void setPolicyProceduresKey(Integer policyProceduresKey) {
		this.policyProceduresKey = policyProceduresKey;
	}

	public Policy getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Policy policyid) {
		this.policyId = policyid;
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

	public PolicyActionCptLookUp getPolicyAction() {
		return policyAction;
	}

	public void setPolicyAction(PolicyActionCptLookUp policyAction) {
		this.policyAction = policyAction;
	}

	public Integer getExcludeb() {
		return excludeb;
	}

	public void setExcludeb(Integer excludeb) {
		this.excludeb = excludeb;
	}

	public Integer getDxLink() {
		return dxLink;
	}

	public void setDxLink(Integer dxLink) {
		this.dxLink = dxLink;
	}
}
