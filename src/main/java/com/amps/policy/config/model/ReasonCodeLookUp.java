package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reasoncode_lkp", schema = "policy")
public class ReasonCodeLookUp {

	@Id
	@Column(name = "reason_code")
	private String reasonCode;

	@Column(name = "reason_desc")
	private String reasonDesc;

	@Column(name = "hipaa_code")
	private String hipaaCode;

	@Column(name = "hippa_desc")
	private String hipaaDesc;

	@Column(name = "deactivated_b")
	private Integer deactivatedb;

	@Column(name = "challenge_code")
	private String challengeCode;

	@Column(name = "challenge_desc")
	private String challengeDesc;

	@Column(name = "custom_b")
	private Integer customb;

	@Column(name = "pco_code")
	private String pcoCode;

    public ReasonCodeLookUp(String reasonCodeFk) {
    }
	public ReasonCodeLookUp(){

	}

    public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonDesc() {
		return reasonDesc;
	}

	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	public String getHipaaCode() {
		return hipaaCode;
	}

	public void setHipaaCode(String hipaaCode) {
		this.hipaaCode = hipaaCode;
	}

	public String getHippaDesc() {
		return hipaaDesc;
	}

	public void setHippaDesc(String hippaDesc) {
		this.hipaaDesc = hippaDesc;
	}

	public Integer getDeactivatedb() {
		return deactivatedb;
	}

	public void setDeactivatedb(Integer deactivatedb) {
		this.deactivatedb = deactivatedb;
	}

	public String getChallengeCode() {
		return challengeCode;
	}

	public void setChallengeCode(String challengeCode) {
		this.challengeCode = challengeCode;
	}

	public String getChallengeDesc() {
		return challengeDesc;
	}

	public void setChallengeDesc(String challengeDesc) {
		this.challengeDesc = challengeDesc;
	}

	public Integer getCustomb() {
		return customb;
	}

	public void setCustomb(Integer customb) {
		this.customb = customb;
	}

	public String getPcoCode() {
		return pcoCode;
	}

	public void setPcoCode(String pcoCode) {
		this.pcoCode = pcoCode;
	}

}