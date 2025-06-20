package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cpt_master", schema = "source")
public class CptMaster {

	@Id
	@Column(name = "cpt_code")
	private String cptCode;

	@Column(name = "short_desc")
	private String shortDesc;

	@Column(name = "med_desc")
	private String medDesc;

	@Column(name = "long_desc")
	private String longDesc;

	@Column(name = "cpt_orgin_code")
	private String cptOriginCode;

	public String getCptCode() {
		return cptCode;
	}

	public void setCptCode(String cptCode) {
		this.cptCode = cptCode;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getMedDesc() {
		return medDesc;
	}

	public void setMedDesc(String medDesc) {
		this.medDesc = medDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getCptOriginCode() {
		return cptOriginCode;
	}

	public void setCptOriginCode(String cptOriginCode) {
		this.cptOriginCode = cptOriginCode;
	}

}
