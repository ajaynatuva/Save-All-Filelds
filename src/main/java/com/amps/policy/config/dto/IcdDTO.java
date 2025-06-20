package com.amps.policy.config.dto;

public class IcdDTO {
	private String diagnosisIcd;

	private String admittingIcd;

	private String externalCauseOfInjuryIcd;

	public String getDiagnosisIcd() {
		return diagnosisIcd;
	}

	public void setDiagnosisIcd(String diagnosisIcd) {
		this.diagnosisIcd = diagnosisIcd;
	}

	public String getAdmittingIcd() {
		return admittingIcd;
	}

	public void setAdmittingIcd(String admittingIcd) {
		this.admittingIcd = admittingIcd;
	}

	public String getExternalCauseOfInjuryIcd() {
		return externalCauseOfInjuryIcd;
	}

	public void setExternalCauseOfInjuryIcd(String externalCauseOfInjuryIcd) {
		this.externalCauseOfInjuryIcd = externalCauseOfInjuryIcd;
	}

}
