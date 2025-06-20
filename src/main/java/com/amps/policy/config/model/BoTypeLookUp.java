package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bo_type_lkp", schema = "source")
public class BoTypeLookUp {
		
			@Id
			@Column(name = "bo_key")
			private Integer boKey;

			@Column(name = "bo_desc")
			private String boDesc;

			public Integer getBoKey() {
				return boKey;
			}

			public void setBoKey(Integer boKey) {
				this.boKey = boKey;
			}

			public String getBoDesc() {
				return boDesc;
			}

			public void setBoDesc(String boDesc) {
				this.boDesc = boDesc;
			}
			
			


}
