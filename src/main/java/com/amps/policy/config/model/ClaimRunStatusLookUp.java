package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "ipu_claim_run_status_lkp", schema = "ipuclaims")
public class ClaimRunStatusLookUp {
	@Id
	@Column(name = "ipu_claim_run_status_lkp_id")
	public Integer ipuClaimRunStatusId;
	
	@Column(name = "status")
	public String Status;

	public Integer getIpuClaimRunStatusId() {
		return ipuClaimRunStatusId;
	}

	public void setIpuClaimRunStatusId(Integer ipuClaimRunStatusId) {
		this.ipuClaimRunStatusId = ipuClaimRunStatusId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	
}


