package com.amps.policy.config.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MIN_MAX_AGE_LKP", schema = "policy")
public class MinMaxAgeLookUp {
	@Id
	@SequenceGenerator(name = "policy.min_max_age_lkp_id_seq", sequenceName = "policy.min_max_age_lkp_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.min_max_age_lkp_id_seq")
	@Column(name = "MIN_MAX_AGE_LKP_ID", updatable = false)
	private Integer minMaxAgeLkpId;

	@Column(name = "MIN_MAX_AGE_DESC")
	private String minMaxAgeDesc;

	@Column(name = "AGE_YEARS")
	private Integer ageYears;

	@Column(name = "AGE_MONTHS")
	private Integer ageMonths;

	@Column(name = "AGE_DAYS")
	private Integer ageDays;

	@Column(name = "EQUALS_B")
	private Integer equalsB;

	@Column(name = "MIN_VS_MAX_B")
	private Integer minVsMaxB;

    public MinMaxAgeLookUp(Integer minAgeFk) {
    }
	public MinMaxAgeLookUp(){

	}

    public Integer getMinMaxAgeLkpId() {
		return minMaxAgeLkpId;
	}

	public void setMinMaxAgeLkpId(Integer minMaxAgeLkpId) {
		this.minMaxAgeLkpId = minMaxAgeLkpId;
	}

	public String getMinMaxAgeDesc() {
		return minMaxAgeDesc;
	}

	public void setMinMaxAgeDesc(String minMaxAgeDesc) {
		this.minMaxAgeDesc = minMaxAgeDesc;
	}

	public Integer getAgeYears() {
		return ageYears;
	}

	public void setAgeYears(Integer ageYears) {
		this.ageYears = ageYears;
	}

	public Integer getAgeMonths() {
		return ageMonths;
	}

	public void setAgeMonths(Integer ageMonths) {
		this.ageMonths = ageMonths;
	}

	public Integer getAgeDays() {
		return ageDays;
	}

	public void setAgeDays(Integer ageDays) {
		this.ageDays = ageDays;
	}

	public Integer getEqualsB() {
		return equalsB;
	}

	public void setEqualsB(Integer equalsB) {
		this.equalsB = equalsB;
	}

	public Integer getMinVsMaxB() {
		return minVsMaxB;
	}

	public void setMinVsMaxB(Integer minVsMaxB) {
		this.minVsMaxB = minVsMaxB;
	}

}