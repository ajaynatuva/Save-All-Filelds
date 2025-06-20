package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "modifier_pay_percentage", schema = "policy")
public class ModifierPayPercentage {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "mpp_key_fk")
	private Integer mppKeyFk;

	@Column(name = "modifier")
	private String modifier;

	@Column(name = "allowed_percentage")
	private String allowedPercentage;

	@Column(name = "pre_op")
	private Integer preOp;

	@Column(name = "intra_op")
	private Integer intraOp;

	@Column(name = "post_op")
	private Integer postOp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMppKeyFk() {
		return mppKeyFk;
	}

	public void setMppKeyFk(Integer mppKeyFk) {
		this.mppKeyFk = mppKeyFk;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getAllowedPercentage() {
		return allowedPercentage;
	}

	public void setAllowedPercentage(String allowedPercentage) {
		this.allowedPercentage = allowedPercentage;
	}

	public Integer getPreOp() {
		return preOp;
	}

	public void setPreOp(Integer preOp) {
		this.preOp = preOp;
	}

	public Integer getIntraOp() {
		return intraOp;
	}

	public void setIntraOp(Integer intraOp) {
		this.intraOp = intraOp;
	}

	public Integer getPostOp() {
		return postOp;
	}

	public void setPostOp(Integer postOp) {
		this.postOp = postOp;
	}

}
