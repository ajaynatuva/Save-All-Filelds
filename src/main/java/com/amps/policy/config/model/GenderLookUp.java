package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gender_lkp", schema = "policy")
public class GenderLookUp {

	@Id
	@Column(name = "gender_code")
	private Integer genderCode;

	@Column(name = "gender_desc")
	private String genderDesc;

    public GenderLookUp() {
    }

    public Integer getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(Integer genderCode) {
		this.genderCode = genderCode;
	}

	public String getGenderDesc() {
		return genderDesc;
	}

	public void setGenderDesc(String genderDesc) {
		this.genderDesc = genderDesc;
	}

}
