package com.amps.policy.config.dto;

import java.util.List;

public class ClaimRowsDTO {

	List<ClaimSearchResultDTO> claimSearchResult;
	
	Integer numberOfRows;
	
	
	public List<ClaimSearchResultDTO> getClaimSearchResult() {
		return claimSearchResult;
	}
	public void setClaimSearchResult(List<ClaimSearchResultDTO> claimSearchResult) {
		this.claimSearchResult = claimSearchResult;
	}
	public Integer getNumberOfRows() {
		return numberOfRows;
	}
	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	
}
