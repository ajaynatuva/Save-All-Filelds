package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bo_policy_lkp", schema = "source")
public class AddOnCodes {

		@Id
		@Column(name = "bo_key")
		private String boKey;

		@Column(name = "bo_desc")
		private String boDesc;

		public String getBoKey() {
			return boKey;
		}

		public void setBoKey(String boKey) {
			this.boKey = boKey;
		}

		public String getBoDesc() {
			return boDesc;
		}

		public void setBoDesc(String boDesc) {
			this.boDesc = boDesc;
		}

		


	}

