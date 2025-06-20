package com.amps.policy.config.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.amps.policy.config.dao.ProceduresDao;
import com.amps.policy.config.dto.ProceduresDTO;
import com.amps.policy.config.dto.ProceduresRowsDTO;
import com.amps.policy.config.dto.ProceduresSearchDTO;
import com.amps.policy.config.dto.ProceduresSearchResultDTO;
import com.amps.policy.config.model.Procedures;

@Component
public class ProceduresDaoImpl implements ProceduresDao {

	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	@Autowired
	JdbcTemplate ipuJdbcTemplate;

	@Transactional(readOnly = true)
	public List<ProceduresDTO> findByProceduresData(int policyId) {
		String findQuery = """
				SELECT policy_id AS policyId, cpt_from AS cptFrom, cpt_to AS cptTo,
								 mod1, mod2, mod3, days_lo AS daysLo, days_hi AS daysHi, rev_from AS revFrom,
								 rev_to AS revTo, pos, dos_from AS dosFrom, dos_to AS dosTo,
								 action_fk AS policyCptActionKey, exclude_b AS excludeb, dx_link AS dxLink,
								 clm_link_fk AS claimLinkKey,clm.claim_link_code as claimLinkCode,clm.claim_link_desc,
								 cpt.policy_cpt_action_code FROM policy.policy_procedures
								 LEFT JOIN POLICY.claim_link_lkp AS clm on clm.claim_link_key = clm_link_fk
								 LEFT JOIN POLICY.policy_cpt_action_lkp AS cpt ON cpt.policy_cpt_action_key = action_fk
								 WHERE policy_id =:policyId
				""";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);

		return ipuNamedParameterJdbcTemplate.query(findQuery, namedParameters,
				new BeanPropertyRowMapper<>(ProceduresDTO.class));
	}

	@Override
	@Transactional
	public List<ProceduresDTO> findByPolicyId(int policyId) {
		String findQuery = """
				select procs.* , action_fk as policyCptActionKey, exclude_b as excludeb, dx_link as dxLink,
				clm_link_fk as claimLinkKey from policy.policy_procedures as procs
				where policy_id = :policyId
				""";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		return ipuNamedParameterJdbcTemplate.query(findQuery, namedParameters, new BeanPropertyRowMapper<ProceduresDTO>(ProceduresDTO.class));
	}

	@Override
	@Transactional
	public void deleteByPolicyId(Integer policyId) {
		String deleteQuery = "delete from policy.policy_procedures where policy_id = :policyId";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(deleteQuery, namedParameters);
	}

	@Override
	@Transactional
	public void saveAllStageData(Integer policyId) {
		String saveQuery = """
				INSERT INTO
				policy.policy_procedures(policy_id,cpt_from,cpt_to,mod1,mod2,mod3,days_lo,days_hi,rev_from,rev_to,
				pos,dos_from,dos_to,action_fk,exclude_b,dx_link, clm_link_fk)
				SELECT
				policy_id,cpt_from,cpt_to,mod1,mod2,mod3,days_lo,days_hi,rev_from,rev_to,
				pos,dos_from,dos_to,action_fk,exclude_b,dx_link,clm_link_fk
				FROM etl.stage_policy_procedures procs where procs.policy_id = :policyId
				""";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(saveQuery, namedParameters);
	}

	@Override
	@Transactional
	public List<Procedures> getAllData() {
		String findQuery = """
		  				select procs.* from policy.policy_procedures as procs
		""";
		return ipuNamedParameterJdbcTemplate.query(findQuery, new BeanPropertyRowMapper<Procedures>(Procedures.class));
	}

	@Override
	public void PostProceduresData(List<ProceduresDTO> postProceduresData) {
		// TODO Auto-generated method stub
		String Query = """
		INSERT INTO policy.policy_procedures(policy_id, cpt_from, cpt_to, mod1, mod2, mod3, days_lo, days_hi, 
  			rev_from, rev_to, pos, dos_from, dos_to, action_fk, exclude_b, dx_link, clm_link_fk) 
  			VALUES (:policyId,:cptFrom,:cptTo,:mod1,:mod2,:mod3,:daysLo,:daysHi,:revFrom,:revTo,:pos,:dosFrom,:dosTo,
  			:policyCptActionKey,:excludeb,:dxLink,:claimLinkKey)
		""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(postProceduresData.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(Query, params);
	}
	
	@Override
	public ProceduresRowsDTO proceduresSearchCriteria(ProceduresSearchDTO proceduresSearchDTO) {
		// TODO Auto-generated method stub
		ProceduresRowsDTO proceduresRowsDTO = new ProceduresRowsDTO();
		String query = "";

		if (proceduresSearchDTO.getPolicyId() != null) {
			query += " AND policy_id = " + proceduresSearchDTO.getPolicyId();
		}
		if (proceduresSearchDTO.getCptFrom() != null && proceduresSearchDTO.getCptFrom() != "") {
			query += " AND cpt_from::text ilike " + "'%" + proceduresSearchDTO.getCptFrom() + "%'";
		}
		if (proceduresSearchDTO.getCptTo() != null && proceduresSearchDTO.getCptTo() != "") {
			query += " AND cpt_to::text ilike " + "'%" + proceduresSearchDTO.getCptTo() + "%'";
		}
		if (proceduresSearchDTO.getMod1() != null && proceduresSearchDTO.getMod1() != "") {
			query += " AND mod1::text ilike " + "'%" + proceduresSearchDTO.getMod1() + "%'";
		}
		if (proceduresSearchDTO.getMod2() != null && proceduresSearchDTO.getMod2() != "") {
			query += " AND mod2::text ilike " + "'%" + proceduresSearchDTO.getMod2() + "%'";
		}
		if (proceduresSearchDTO.getMod3() != null && proceduresSearchDTO.getMod3() != "") {
			query += " AND mod3::text ilike " + "'%" + proceduresSearchDTO.getMod3() + "%'";
		}
		if (proceduresSearchDTO.getDaysLo() != null) {
			query += " AND days_lo = " + proceduresSearchDTO.getDaysLo();
		}
		if (proceduresSearchDTO.getDaysHi() != null) {
			query += " AND days_hi = " + proceduresSearchDTO.getDaysHi();
		}
		if (proceduresSearchDTO.getRevFrom() != null && proceduresSearchDTO.getRevFrom() != "") {
			query += " AND rev_from::text ilike " + "'%" + proceduresSearchDTO.getRevFrom() + "%'";
		}
		if (proceduresSearchDTO.getRevTo() != null && proceduresSearchDTO.getRevTo() != "") {
			query += " AND rev_to::text ilike " + "'%" + proceduresSearchDTO.getRevTo() + "%'";
		}
		if (proceduresSearchDTO.getPos() != null && proceduresSearchDTO.getPos() != "") {
			query += " AND pos::text ilike " + "'%" + proceduresSearchDTO.getPos() + "%'";
		}
		if (proceduresSearchDTO.getDosFrom() != null && proceduresSearchDTO.getDosFrom() != "") {
			query += " AND dos_from = " + "'" + proceduresSearchDTO.getDosFrom() + "'";
		}
		if (proceduresSearchDTO.getDosTo() != null && proceduresSearchDTO.getDosTo() != "") {
			query += " AND dos_to = " + "'" + proceduresSearchDTO.getDosTo() + "'";
		}
		if (proceduresSearchDTO.getExcludeb() != null) {
			query += " AND exclude_b = " + proceduresSearchDTO.getExcludeb();
		}
		if (proceduresSearchDTO.getDxLink() != null) {
			query += " AND dx_link = " + proceduresSearchDTO.getDxLink();
		}

		if (proceduresSearchDTO.getPolicyCptActionCode() != null
				&& proceduresSearchDTO.getPolicyCptActionCode() != "") {
			query += " AND cpt.policy_cpt_action_code :: text ilike " + "'%"
					+ proceduresSearchDTO.getPolicyCptActionCode() + "%'";
		}

		if (proceduresSearchDTO.getClaimLinkCode() != null && proceduresSearchDTO.getClaimLinkCode() != "") {
			query += " AND clm.claim_link_code :: text ilike " + "'%" + proceduresSearchDTO.getClaimLinkCode() + "%'";
		}
		String numOfRowsQuery = """
				select count(1) from policy.policy_procedures  LEFT JOIN POLICY.claim_link_lkp AS clm on clm.claim_link_key = clm_link_fk
				LEFT JOIN POLICY.policy_cpt_action_lkp AS cpt ON cpt.policy_cpt_action_key = action_fk  where 1=1 
				""";

		numOfRowsQuery += query;

		Integer numberOfRows = ipuJdbcTemplate.queryForObject(numOfRowsQuery, Integer.class);

		query = """
				SELECT policy_id AS policyId, cpt_from AS cptFrom, cpt_to AS cptTo,
				 mod1, mod2, mod3, days_lo AS daysLo, days_hi AS daysHi, rev_from AS revFrom,
				 rev_to AS revTo, pos, dos_from AS dosFrom, dos_to AS dosTo,
				 action_fk AS policyCptActionKey, exclude_b AS excludeb, dx_link AS dxLink,
				 clm_link_fk AS claimLinkKey,clm.claim_link_code as claimLinkCode,clm.claim_link_desc,
				 cpt.policy_cpt_action_code FROM policy.policy_procedures
				 LEFT JOIN POLICY.claim_link_lkp AS clm on clm.claim_link_key = clm_link_fk
				 LEFT JOIN POLICY.policy_cpt_action_lkp AS cpt ON cpt.policy_cpt_action_key = action_fk
				 WHERE 1=1
				 """ + query;

		if (proceduresSearchDTO.getIsSort() != null && proceduresSearchDTO.getIsSort() != "") {
			if (proceduresSearchDTO.getSortColumn() != null && proceduresSearchDTO.getSortColumn() != "") {
				query += " order by " + proceduresSearchDTO.getSortColumn() + " " + proceduresSearchDTO.getIsSort();
			}
		}

		query += " limit 1000 offset " + proceduresSearchDTO.getEndRow();
		List<ProceduresSearchResultDTO> resultSet = ipuNamedParameterJdbcTemplate.query(query,
				new BeanPropertyRowMapper<>(ProceduresSearchResultDTO.class));

		proceduresRowsDTO.setProceduresSearchResult(resultSet);
		proceduresRowsDTO.setNumberOfRows(numberOfRows);

		return proceduresRowsDTO;

	}
}

