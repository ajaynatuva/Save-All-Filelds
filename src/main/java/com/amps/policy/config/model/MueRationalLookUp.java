package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mue_rationale_lkp", schema = "source")
public class MueRationalLookUp {
	
		@Id
		@Column(name = "mue_rationale_key")
		private Integer mueRationalKey;

		@Column(name = "description")
		private String description;

		public Integer getMueRationalKey() {
			return mueRationalKey;
		}

		public void setMueRationalKey(Integer mueRationalKey) {
			this.mueRationalKey = mueRationalKey;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		
		

}
