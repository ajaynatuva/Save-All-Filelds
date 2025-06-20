package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.StageProceduresDao;
import com.amps.policy.config.dto.ProceduresDTO;
import com.amps.policy.config.dto.ProcsDeltaDTO;
import com.amps.policy.config.helper.ProceduresStageDeltaReportHelper;
import com.amps.policy.config.model.StageProcedures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.amps.policy.config.queries.StageProceduresDeltaQuery.StageProceduresDeltaQuery;

@Component
public class StageProceduresDaoImpl implements StageProceduresDao {

	@Autowired
	ProceduresStageDeltaReportHelper proceduresStageDeltaReportHelper;
	
	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	@Autowired
	JdbcTemplate ipuJdbcTemplate;

	@Override
	@Transactional
	public boolean generateDeltaReport(Integer policyId, String dropboxDeltaPath) {
		try {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("policyId", policyId);
			List<ProcsDeltaDTO> resultSet = ipuNamedParameterJdbcTemplate.query(StageProceduresDeltaQuery, namedParameters,
					new BeanPropertyRowMapper<ProcsDeltaDTO>(ProcsDeltaDTO.class));
			String deltaFileName = "Delta_Procedures.xlsx";
			proceduresStageDeltaReportHelper.createXLS(resultSet, dropboxDeltaPath, deltaFileName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public void deleteAllData() {
		String deleteQuery = """
								delete from etl.stage_policy_procedures
							""";
		ipuJdbcTemplate.execute(deleteQuery);
	}

	@Override
	@Transactional
	public List<StageProcedures> getAllData() {
		String findQuery = """
				select procs.* from etl.stage_policy_procedures as procs
				""";
		return ipuJdbcTemplate.query(findQuery, new BeanPropertyRowMapper<StageProcedures>(StageProcedures.class));
	}
	
	@Override
	@Transactional
	public void saveData(List<ProceduresDTO> proceduresDTO) {
		String query = """
    INSERT INTO etl.stage_policy_procedures(policy_id, cpt_from, cpt_to, mod1, mod2, mod3, days_lo, days_hi, rev_from, 
    rev_to, pos, dos_from, dos_to, action_fk, exclude_b, dx_link, clm_link_fk)
	VALUES ( :policyId, :cptFrom, :cptTo, :mod1, :mod2, :mod3, :daysLo, :daysHi, :revFrom, :revTo, :pos, :dosFrom, :dosTo, :policyCptActionKey, :excludeb, :dxLink, :claimLinkKey)
	""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(proceduresDTO.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(query, params);
	}

	@Override
	@Transactional
	public void deleteByPolicyId(Integer policyId) {
		String deleteQuery = """
  								delete from etl.stage_policy_procedures where policy_id = :policyId
 							 """;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(deleteQuery, namedParameters);
	}

	
}
