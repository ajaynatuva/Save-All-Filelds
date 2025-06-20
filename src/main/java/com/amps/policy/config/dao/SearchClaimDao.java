package com.amps.policy.config.dao;

import com.amps.policy.config.dto.ClaimSearchDTO;
import com.amps.policy.config.dto.ClaimSearchResultDTO;
import com.amps.policy.config.dto.mapper.ClaimSearchResultDTOMapper;

import java.util.List;

public interface SearchClaimDao {

	public List<ClaimSearchResultDTO> getClaimResults(ClaimSearchDTO claimSearchDto);
	
	public Integer getClaimResultSize(ClaimSearchDTO claimSearchDto);
}
