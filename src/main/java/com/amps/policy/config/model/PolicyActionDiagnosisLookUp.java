package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@Entity
	@Table(name = "policy_diagnosis_action_lkp", schema = "policy")
	public class PolicyActionDiagnosisLookUp {

		@Id
		@Column(name = "policy_diagnosis_action_key")
		private Integer policyDiagnosisActionKey;

		@Column(name = "policy_diagnosis_action_code")
		private String policyDiagnosisActionCode;

		@Column(name = "policy_diagnosis_action_desc")
		private String policyDiagnosisActionDesc;

		public Integer getPolicyDiagnosisActionKey() {
			return policyDiagnosisActionKey;
		}

		public void setPolicyDiagnosisActionKey(Integer policyDiagnosisActionKey) {
			this.policyDiagnosisActionKey = policyDiagnosisActionKey;
		}

		public String getPolicyDiagnosisActionCode() {
			return policyDiagnosisActionCode;
		}

		public void setPolicyDiagnosisActionCode(String policyDiagnosisActionCode) {
			this.policyDiagnosisActionCode = policyDiagnosisActionCode;
		}

		public String getPolicyDiagnosisActionDesc() {
			return policyDiagnosisActionDesc;
		}

		public void setPolicyDiagnosisActionDesc(String policyDiagnosisActionDesc) {
			this.policyDiagnosisActionDesc = policyDiagnosisActionDesc;
		}

		

		

}
