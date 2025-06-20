package com.amps.policy.config.dto;

import java.util.List;

public class ProceduresRowsDTO {

	List<ProceduresSearchResultDTO> proceduresSearchResult;

	Integer numberOfRows;

	public List<ProceduresSearchResultDTO> getProceduresSearchResult() {
		return proceduresSearchResult;
	}

	public void setProceduresSearchResult(List<ProceduresSearchResultDTO> proceduresSearchResult) {
		this.proceduresSearchResult = proceduresSearchResult;
	}

	public Integer getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

}
