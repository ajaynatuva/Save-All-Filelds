package com.amps.policy.config.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "policy_dx", schema = "policy")
public class Diagnosis {

	@Id
	@SequenceGenerator(name = "policy.policy_diagnosis_policy_id_seq", sequenceName = "policy.policy_diagnosis_policy_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy.policy_diagnosis_policy_id_seq")

	@Column(name = "policy_diagnosis_key")
	private Integer policyDiagnosisKey;

	@Column(name = "policy_id")
	private Integer policyId;

	@Column(name = "diag_from")
	private String diagFrom;

	@Column(name = "diag_to")
	private String diagTo;

	@Column(name = "dos_from")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dosFrom;

	@Column(name = "dos_to")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dosTo;

	@Column(name = "action")
	private Integer action;

	@Column(name = "exclusion_b")
	private Integer exclusion;

	@Column(name = "header_level_b")
	private int headerLevel;

	@Column(name = "principal_dx_b")
	private int principalDx;

	@Column(name = "only_dx_b")
	private Integer onlyDx;

	public Integer getPolicyDiagnosisKey() {
		return policyDiagnosisKey;
	}

	public void setPolicyDiagnosisKey(Integer policyDiagnosisKey) {
		this.policyDiagnosisKey = policyDiagnosisKey;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getDiagFrom() {
		return diagFrom;
	}

	public void setDiagFrom(String diagFrom) {
		this.diagFrom = diagFrom;
	}

	public String getDiagTo() {
		return diagTo;
	}

	public void setDiagTo(String diagTo) {
		this.diagTo = diagTo;
	}

	public LocalDate getDosFrom() {
		return dosFrom;
	}

	public void setDosFrom(LocalDate dosFrom) {
		this.dosFrom = dosFrom;
	}

	public LocalDate getDosTo() {
		return dosTo;
	}

	public void setDosTo(LocalDate dosTo) {
		this.dosTo = dosTo;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public Integer getExclusion() {
		return exclusion;
	}

	public void setExclusion(Integer exclusion) {
		this.exclusion = exclusion;
	}

	public int getHeaderLevel() {
		return headerLevel;
	}

	public void setHeaderLevel(int headerLevel) {
		this.headerLevel = headerLevel;
	}

	public int getPrincipalDx() {
		return principalDx;
	}

	public void setPrincipalDx(int principalDx) {
		this.principalDx = principalDx;
	}

	public Integer getOnlyDx() {
		return onlyDx;
	}

	public void setOnlyDx(Integer onlyDx) {
		this.onlyDx = onlyDx;
	}

	@Override
	public String toString() {
		return "Diagnosis [policyDiagnosisKey=" + policyDiagnosisKey + ", policyId=" + policyId + ", diagFrom="
				+ diagFrom + ", diagTo=" + diagTo + ", dosFrom=" + dosFrom + ", dosTo=" + dosTo + ", action=" + action
				+ ", exclusion=" + exclusion + ", headerLevel=" + headerLevel + ", principalDx=" + principalDx + "]";
	}

}