package com.amps.policy.config.dao.impl;

import static com.amps.policy.config.queries.StageNpiDeltaQuery.StageNpiDeltaQuery;
import static com.amps.policy.config.queries.StageNpiDeltaQuery.NpiExceptionsQuery;
import static com.amps.policy.config.queries.StageNpiDeltaQuery.NpiQuery;
import java.util.List;
import com.amps.policy.config.dto.NPIDeltaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.amps.policy.config.dao.NpiDao;
import com.amps.policy.config.dto.NpiDTO;
import com.amps.policy.config.helper.NpiStageDeltaReportHelper;
import com.amps.policy.config.model.Npi;

@Component
public class NpiDaoImpl implements NpiDao {
	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	@Autowired
	JdbcTemplate ipuJdbcTemplate;

	@Autowired
	NpiStageDeltaReportHelper NpiStageDeltaReportHelper;

	public void saveNpiDataToSource(List<Npi> npiData) {
		String Query = """
				INSERT INTO policy.client_npi_exclusions(policy_id_fk, client_group_id, npi, lob, claim_type,
				deleted_b, created_date, updated_date)
				VALUES (:policyIdFk, :clientGroupId, :npi, :lob, :claimType, 0, now(), now())
				""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(npiData.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(Query, params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteClientNpiExclusionsByPolicyId(List<Npi> npiList) {
		String deleteQuery = """
				update policy.client_npi_exclusions set deleted_b = 1 ,updated_date = now() where
				npi =:npi and policy_id_fk =:policyIdFk
				and client_group_id =:clientGroupId and claim_type =:claimType and lob =:lob
				""";
		SqlParameterSource[] deletedParams = SqlParameterSourceUtils.createBatch(npiList);
		ipuNamedParameterJdbcTemplate.batchUpdate(deleteQuery, deletedParams);
	}

	@Override
	public List<Npi> getNpiGroupedDetails(Integer policyId) {
		String query = """
				SELECT
				    MIN(npi_key) AS npi_key,
				    policy_id_fk,
				    client_group_id,
				    MIN(lob) as lob,
				    npi as npi,
				    STRING_AGG(claim_type, ', ') AS claim_type,
				    MIN(deleted_b) AS deleted_b,
				    MIN(created_date) AS created_date,
				    MIN(updated_date) AS updated_date
				FROM policy.client_npi_exclusions where policy_id_fk=:policyId
				GROUP BY policy_id_fk, client_group_id, lob, npi,deleted_b order by deleted_b ASC;
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		return ipuNamedParameterJdbcTemplate.query(query, namedParameters, new BeanPropertyRowMapper<>(Npi.class));
	}

	// import functionality changes
	@Override
	public void deleteStageData(Integer policyId) {
		// TODO Auto-generated method stub
		String query = """
				Delete from etl.client_npi_exclusions_stage where policy_id_fk =:policyId
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(query, namedParameters);

	}

	@Override
	public void deleteDeltaData(Integer policyId) {
		// TODO Auto-generated method stub
		String query = """
				Delete from etl.client_npi_exclusions_delta where policy_id_fk =:policyId
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(query, namedParameters);
	}

	@Override
	public void loadDataToStage(List<NpiDTO> npiDTO) {
		// TODO Auto-generated method stub
		String query = """
				INSERT INTO etl.client_npi_exclusions_stage(
				policy_id_fk, client_group_id, lob, claim_type, npi, deleted_b)
				VALUES (:policyId, :clientGroupId, :lob,:claimType,:npi,:deletedB);
				""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(npiDTO.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(query, params);
	}

	@Override
	public void saveAllStageDataToTarget(Integer policyId) {
		// TODO Auto-generated method stub

		String saveQuery = """
				            INSERT INTO policy.client_npi_exclusions (policy_id_fk, client_group_id, npi, lob, claim_type,
				            deleted_b, created_date, updated_date)
				            SELECT policy_id_fk, client_group_id, npi, lob, claim_type, deleted_b, now(), now()
				            FROM etl.client_npi_exclusions_delta WHERE policy_id_fk =:policyId and delta_action = 'ADD'
				           """;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(saveQuery, namedParameters);
	}

	@Override
	public List<Npi> getAllDeleteActionNPIData(Integer policyId) {
		// TODO Auto-generated method stub
		String getQuery = """
				    select policy_id_fk as policyIdFk,* from etl.client_npi_exclusions_delta
				    where policy_id_fk =:policyId and delta_action = 'DELETE'
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		return ipuNamedParameterJdbcTemplate.query(getQuery, namedParameters, new BeanPropertyRowMapper<>(Npi.class));
	}
	public boolean generateDeltaReport(Integer policyId, String sharePointDeltaPath) {
		try {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("policyId", policyId);
			ipuNamedParameterJdbcTemplate.update(StageNpiDeltaQuery, namedParameters);
			List<NPIDeltaDTO> resultSet = ipuNamedParameterJdbcTemplate.query(NpiQuery, namedParameters,
					new BeanPropertyRowMapper<>(NPIDeltaDTO.class));
			String deltaFileName = "Delta_Npi.xlsx";
			NpiStageDeltaReportHelper.createXLS(resultSet, sharePointDeltaPath, deltaFileName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean generateExceptionReport(Integer policyId, String sharePointDeltaPath) {
		try {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("policyId", policyId);

			List<NPIDeltaDTO> resultSet = ipuNamedParameterJdbcTemplate.query(NpiExceptionsQuery, namedParameters,
					new BeanPropertyRowMapper<>(NPIDeltaDTO.class));

			if (!resultSet.isEmpty()) {
				String NpiExceptionsFile = "Exceptions_Npi.xlsx";
				NpiStageDeltaReportHelper.createXLS(resultSet, sharePointDeltaPath, NpiExceptionsFile);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
