package com.amps.policy.config.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "policy_category_lkp", schema = "policy")
public class PolicyCategoryLookUp {

	@Id
	@SequenceGenerator(name = "policy.policy_category_id_seq", sequenceName = "policy.policy_category_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.policy_category_id_seq")
	@Column(name = "policy_category_lkp_id", updatable = false)
	private Integer policyCategoryLkpId;

	@Column(name = "policy_category_desc")
	private String policyCategoryDesc;

	@Column(name = "priority")
	private Integer priority;

	@Column(name = "hard_denial_B")
	private Integer hardDenialB;

	@Column(name = "last_updated_at")
	private Date lastUpdatedAt;

	@Column(name = "co1")
	private String co1;

	@Column(name = "co2")
	private String co2;

	@Column(name = "co3")
	private String co3;

	@Column(name = "co4")
	private String co4;

	@Column(name = "co5")
	private String co5;

    public PolicyCategoryLookUp(Integer policyCategoryLkpId) {
    }
	public PolicyCategoryLookUp(){

	}

    public String getCo1() {
		return co1;
	}

	public void setCo1(String co1) {
		this.co1 = co1;
	}

	public String getCo2() {
		return co2;
	}

	public void setCo2(String co2) {
		this.co2 = co2;
	}

	public String getCo3() {
		return co3;
	}

	public void setCo3(String co3) {
		this.co3 = co3;
	}

	public String getCo4() {
		return co4;
	}

	public void setCo4(String co4) {
		this.co4 = co4;
	}

	public String getCo5() {
		return co5;
	}

	public void setCo5(String co5) {
		this.co5 = co5;
	}


	public Integer getPolicyCategoryLkpId() {
		return policyCategoryLkpId;
	}

	public void setPolicyCategoryLkpId(Integer policyCategoryLkpId) {
		this.policyCategoryLkpId = policyCategoryLkpId;
	}

	public String getPolicyCategoryDesc() {
		return policyCategoryDesc;
	}

	public void setPolicyCategoryDesc(String policyCategoryDesc) {
		this.policyCategoryDesc = policyCategoryDesc;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getHardDenialB() {
		return hardDenialB;
	}

	public void setHardDenialB(Integer hardDenialB) {
		this.hardDenialB = hardDenialB;
	}

	public Date getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(Date lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

}