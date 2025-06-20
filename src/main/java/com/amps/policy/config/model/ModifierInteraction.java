package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "modifier_interaction", schema = "source")
public class ModifierInteraction {

	@Id
	@Column(name = "mi_key")
	private Integer miKey;

	@Column(name = "mit_key")
	private Integer mitKey;

	@Column(name = "modifier")
	private String modifier;

	@Column(name = "hits_off_same_modifier")
	private Integer hitsOffSameModifier;

	@Column(name = "hits_off_other_modifier")
	private Integer hitsOffOtherModifier;

	@Column(name = "hits_off_blank_modifier")
	private Integer hitsOffBlankModifier;

	@Column(name = "other_hits_off_this_modifier")
	private Integer otherHitsOffThisModifier;

	@Column(name = "blank_hits_off_this_modifier")
	private Integer blankHitsOffThisModifier;

	@Column(name = "modifier_exception")
	private String modifierException;

	public Integer getMiKey() {
		return miKey;
	}

	public void setMiKey(Integer miKey) {
		this.miKey = miKey;
	}

	public Integer getMitKey() {
		return mitKey;
	}

	public void setMitKey(Integer mitKey) {
		this.mitKey = mitKey;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Integer getHitsOffSameModifier() {
		return hitsOffSameModifier;
	}

	public void setHitsOffSameModifier(Integer hitsOffSameModifier) {
		this.hitsOffSameModifier = hitsOffSameModifier;
	}

	public Integer getHitsOffOtherModifier() {
		return hitsOffOtherModifier;
	}

	public void setHitsOffOtherModifier(Integer hitsOffOtherModifier) {
		this.hitsOffOtherModifier = hitsOffOtherModifier;
	}

	public Integer getHitsOffBlankModifier() {
		return hitsOffBlankModifier;
	}

	public void setHitsOffBlankModifier(Integer hitsOffBlankModifier) {
		this.hitsOffBlankModifier = hitsOffBlankModifier;
	}

	public Integer getOtherHitsOffThisModifier() {
		return otherHitsOffThisModifier;
	}

	public void setOtherHitsOffThisModifier(Integer otherHitsOffThisModifier) {
		this.otherHitsOffThisModifier = otherHitsOffThisModifier;
	}

	public Integer getBlankHitsOffThisModifier() {
		return blankHitsOffThisModifier;
	}

	public void setBlankHitsOffThisModifier(Integer blankHitsOffThisModifier) {
		this.blankHitsOffThisModifier = blankHitsOffThisModifier;
	}

	public String getModifierException() {
		return modifierException;
	}

	public void setModifierException(String modifierException) {
		this.modifierException = modifierException;
	}

}
