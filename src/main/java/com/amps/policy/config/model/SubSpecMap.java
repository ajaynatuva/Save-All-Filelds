package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SUBSPEC_MAP", schema = "policy")
public class SubSpecMap {
	
	@Column(name = "SPEC_CODE")
	private String specCode;
	
	@Id
	@Column(name = "SUBSPEC_CODE")
	private String subSpecCode;

	@Column(name = "SUBSPEC_DESC")
	private String subSpecDesc;

	@Column(name = "MISC_B")
	private Integer miscB;

	@Column(name = "TAXONOMY_CODE")
	private String taxonomyCode;

	@Column(name = "CMS_SPECIALITY_CODE")
	private String cmsSpecialityCode;
	
	@Column(name = "DELETED_B")
	private Integer deletedB;

	public String getCmsSpecialityCode() {
		return cmsSpecialityCode;
	}

	public void setCmsSpecialityCode(String cmsSpecialityCode) {
		this.cmsSpecialityCode = cmsSpecialityCode;
	}

	public String getSpecCode() {
		return specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}

	public String getSubSpecCode() {
		return subSpecCode;
	}

	public void setSubSpecCode(String subSpecCode) {
		this.subSpecCode = subSpecCode;
	}

	public String getSubSpecDesc() {
		return subSpecDesc;
	}

	public void setSubSpecDesc(String subSpecDesc) {
		this.subSpecDesc = subSpecDesc;
	}

	public Integer getMiscB() {
		return miscB;
	}

	public void setMiscB(Integer miscB) {
		this.miscB = miscB;
	}

	public String getTaxonomyCode() {
		return taxonomyCode;
	}

	public void setTaxonomyCode(String taxonomyCode) {
		this.taxonomyCode = taxonomyCode;
	}

	public Integer getDeletedB() {
		return deletedB;
	}

	public void setDeletedB(Integer deletedB) {
		this.deletedB = deletedB;
	}

	
	
	

}
