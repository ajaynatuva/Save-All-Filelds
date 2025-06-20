package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "POS_LKP", schema = "source")
public class PosLookUp {
	@Id
	@Column(name = "POS_CODE")
	private String posCode;

	@Column(name = "POS_NAME")
	private String posName;

	@Column(name = "POS_DESC")
	private String posDesc;

	@Column(name = "FACILITY_B")
	private int facilityB;

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getPosDesc() {
		return posDesc;
	}

	public void setPosDesc(String posDesc) {
		this.posDesc = posDesc;
	}

	public int getFacilityB() {
		return facilityB;
	}

	public void setFacilityB(int facilityB) {
		this.facilityB = facilityB;
	}

}
