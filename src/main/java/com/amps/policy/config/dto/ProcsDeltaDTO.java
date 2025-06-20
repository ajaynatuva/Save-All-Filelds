package com.amps.policy.config.dto;

import java.util.Date;


public class ProcsDeltaDTO {
	private String action;
//	private Integer policy_procedures_key;
	private Integer policy_id;
	private String cpt_from;
	private String cpt_to;
	private String mod1;
	private String mod2;
	private String mod3;
	private Integer days_lo;
	private Integer days_hi;
	private String rev_from;
	private String rev_to;
	private String pos;
	private Date dos_from;
	private Date dos_to;
	private Integer action_fk;
	private Integer exclude_b;
	private Integer dx_link;
	private Integer clm_link_fk;


	public Integer getClm_link_fk() {
		return clm_link_fk;
	}

	public void setClm_link_fk(Integer clm_link_fk) {
		this.clm_link_fk = clm_link_fk;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

//	public Integer getPolicy_procedures_key() {
//		return policy_procedures_key;
//	}
//	public void setPolicy_procedures_key(Integer policy_procedures_key) {
//		this.policy_procedures_key = policy_procedures_key;
//	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public String getCpt_from() {
		return cpt_from;
	}

	public void setCpt_from(String cpt_from) {
		this.cpt_from = cpt_from;
	}

	public String getCpt_to() {
		return cpt_to;
	}

	public void setCpt_to(String cpt_to) {
		this.cpt_to = cpt_to;
	}

	public String getMod1() {
		return mod1;
	}

	public void setMod1(String mod1) {
		this.mod1 = mod1;
	}

	public String getMod2() {
		return mod2;
	}

	public void setMod2(String mod2) {
		this.mod2 = mod2;
	}

	public String getMod3() {
		return mod3;
	}

	public void setMod3(String mod3) {
		this.mod3 = mod3;
	}

	public Integer getDays_lo() {
		return days_lo;
	}

	public void setDays_lo(Integer days_lo) {
		this.days_lo = days_lo;
	}

	public Integer getDays_hi() {
		return days_hi;
	}

	public void setDays_hi(Integer days_hi) {
		this.days_hi = days_hi;
	}

	public String getRev_from() {
		return rev_from;
	}

	public void setRev_from(String rev_from) {
		this.rev_from = rev_from;
	}

	public String getRev_to() {
		return rev_to;
	}

	public void setRev_to(String rev_to) {
		this.rev_to = rev_to;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public Date getDos_from() {
		return dos_from;
	}

	public void setDos_from(Date dos_from) {
		this.dos_from = dos_from;
	}

	public Date getDos_to() {
		return dos_to;
	}

	public void setDos_to(Date dos_to) {
		this.dos_to = dos_to;
	}

	public Integer getAction_fk() {
		return action_fk;
	}

	public void setAction_fk(Integer action_fk) {
		this.action_fk = action_fk;
	}

	public Integer getExclude_b() {
		return exclude_b;
	}

	public void setExclude_b(Integer exclude_b) {
		this.exclude_b = exclude_b;
	}
	public Integer getDx_link() {
		return dx_link;
	}

	public void setDx_link(Integer dx_link) {
		this.dx_link = dx_link;
	}

}
