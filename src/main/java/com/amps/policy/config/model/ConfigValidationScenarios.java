package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "config_validation_scenarios", schema = "policy")
public class ConfigValidationScenarios {
	@Id
	@Column(name = "query_id")
	private Integer queryId;
	
	@Column(name = "query")
	private String query;

	public Integer getQueryId() {
		return queryId;
	}

	public void setQueryId(Integer queryId) {
		this.queryId = queryId;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	

}
