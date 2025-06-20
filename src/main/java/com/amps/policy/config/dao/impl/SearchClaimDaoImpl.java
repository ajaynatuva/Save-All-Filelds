package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.SearchClaimDao;
import com.amps.policy.config.dto.ClaimSearchDTO;
import com.amps.policy.config.dto.ClaimSearchResultDTO;
import com.amps.policy.config.dto.mapper.ClaimSearchResultDTOMapper;
import com.amps.policy.config.queries.ClaimSearchQuery;
import com.amps.policy.config.service.ClaimSearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.*;
import static com.amps.policy.config.queries.ClaimSearchQuery.*;


@Component
public class SearchClaimDaoImpl implements SearchClaimDao {

	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	@Autowired
	ClaimSearchQuery claimSearchQuery;

	@Autowired
	JdbcTemplate ipuJdbcTemplate;

	@Autowired
	ClaimSearchService claimSearchService;

	Logger logger = LogManager.getLogger(SearchClaimDaoImpl.class.getName());

	public List<ClaimSearchResultDTO> getClaimResults(ClaimSearchDTO claimSearchDto) {
		String conditionQuery = claimSearchService.claimConditionQuery(claimSearchDto);
		String orderByQuery  = "";
		if (claimSearchDto.getIsSort() != null && claimSearchDto.getIsSort() != "") {
			if (claimSearchDto.getSortColumn() != null && claimSearchDto.getSortColumn() != "") {
				orderByQuery += " order by " + claimSearchDto.getSortColumn() + " " + claimSearchDto.getIsSort();
			}
		}
		String query=ClaimQuery;
		int offSet = claimSearchDto.getEndRow() != null ? claimSearchDto.getEndRow() : 0;
		String limitQuery = " LIMIT 1000 OFFSET " +offSet;
		if((conditionQuery.isEmpty() || conditionQuery == "") && orderByQuery == ""){
			query = query.replace("{LIMIT_WITHOUT_COND}", limitQuery);
			query=query.replace("{LIMIT_WITH_COND}"," ");
			query=query.replace("{CONDITION}"," ");
		}
		else {
			query = query.replace("{LIMIT_WITHOUT_COND}", orderByQuery == "" ? "" : conditionQuery != "" ? "" : limitQuery);
			query=query.replace("{LIMIT_WITH_COND}",orderByQuery == "" ? limitQuery : orderByQuery );
			query=query.replace("{CONDITION}",conditionQuery);
		}
		return ipuNamedParameterJdbcTemplate.query(
				query,
				new ClaimSearchResultDTOMapper()
		);
	}

	public Integer getClaimResultSize(ClaimSearchDTO claimSearchDto) {
		String query = claimSearchService.claimConditionQuery(claimSearchDto);
		int offSet = claimSearchDto.getEndRow() != null ? claimSearchDto.getEndRow() : 0;
		String limitQuery = " LIMIT 1000 OFFSET " +offSet;
		String finalQuery = ClaimQuery;
		if(query.isEmpty() || query == "" ){
			finalQuery = countQuery;
		}else{
			finalQuery = finalQuery.replace("{LIMIT_WITHOUT_COND}", "");
			finalQuery=finalQuery.replace("{LIMIT_WITH_COND}",limitQuery);
			finalQuery=finalQuery.replace("{CONDITION}",query);
		}
		String totalCount=numberOfRowsQuery.replace(":count",finalQuery);
		Integer numberOfRows = ipuJdbcTemplate.queryForObject(totalCount, Integer.class);
		return numberOfRows;
	}
}