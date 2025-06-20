package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "MOD_LKP", schema = "source")
public class ModLookUp {
	
	@Id
	@Column(name = "cpt_mod")
	private String cptMod;

	@Column(name = "description")
	private String description;
	
	@Column(name = "ambulance_mod_b")
	private Integer ambulanceModB;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "is_cci")
	private Integer isCci;

	@Column(name = "is_59_group")
	private Integer is_59_group;

	public String getCptMod() {
		return cptMod;
	}

	public void setCptMod(String cptMod) {
		this.cptMod = cptMod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAmbulanceModB() {
		return ambulanceModB;
	}

	public void setAmbulanceModB(Integer ambulanceModB) {
		this.ambulanceModB = ambulanceModB;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getIsCci() {
		return isCci;
	}

	public void setIsCci(Integer isCci) {
		this.isCci = isCci;
	}

	public Integer getIs_59_group() {
		return is_59_group;
	}

	public void setIs_59_group(Integer is_59_group) {
		this.is_59_group = is_59_group;
	}

	@Override
	public String toString() {
		return "ModLookUp [cptMod=" + cptMod + ", description=" + description + ", ambulanceModB=" + ambulanceModB
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", isCci=" + isCci + ", is_59_group="
				+ is_59_group + "]";
	}

}