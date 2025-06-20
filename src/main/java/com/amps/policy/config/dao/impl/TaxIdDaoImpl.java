package com.amps.policy.config.dao.impl;
import com.amps.policy.config.dao.TaxIdDao;
import com.amps.policy.config.dto.TaxIdDTO;
import com.amps.policy.config.dto.TaxIdDeltaDTO;
import com.amps.policy.config.helper.TaxIdStageDeltaReportHelper;
import com.amps.policy.config.model.TaxId;
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
import java.util.List;
import static com.amps.policy.config.queries.StageTaxIdDeltaQuery.*;

@Component
public class TaxIdDaoImpl implements TaxIdDao {
	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	@Autowired
	TaxIdStageDeltaReportHelper taxIdStageDeltaReportHelper;

	@Autowired
	JdbcTemplate ipuJdbcTemplate;

	@Override
	@Transactional
	public void saveData(List<TaxIdDTO> taxIdDTO) {
		String Query = """
                INSERT INTO etl.client_tin_exclusions_stage(policy_id, client_group_id, tax_id, lob, claim_type, deleted_b)
                VALUES (:policyId, :clientGroupId, :taxId, :lob, :claimType, :deletedB)
                """;
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(taxIdDTO.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(Query, params);
	}

	public void saveTaxIdDataToSource(List<TaxId> taxIdData) {
		String Query = """
                INSERT INTO policy.client_tin_exclusions(policy_id_fk, client_group_id, tax_id, lob, claim_type, deleted_b, created_date, updated_date)
                VALUES (:policyIdFk, :clientGroupId, :taxId, :lob, :claimType, 0, now(), now())
                """;
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(taxIdData.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(Query, params);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteClientTinExclusionsByPolicyId(List<TaxId> taxIdList) {
		String deleteQuery = """
                update policy.client_tin_exclusions set deleted_b = 1 ,updated_date = now() where tax_id=:taxId and policy_id_fk =:policyIdFk
                and client_group_id = :clientGroupId and claim_type = :claimType and lob = :lob
                """;
		SqlParameterSource[] deletedParams = SqlParameterSourceUtils.createBatch(taxIdList);
		ipuNamedParameterJdbcTemplate.batchUpdate(deleteQuery, deletedParams);
	}

	@Override
	public List<TaxId> getAllDeleteActionCTEData(Integer policyId) {
		String getQuery = """
                    select policy_id as policyIdFk,* from etl.client_tin_exclusions_delta where policy_id = :policyId and delta_action = 'DELETE'
                """;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		return ipuNamedParameterJdbcTemplate.query(getQuery, namedParameters,
				new BeanPropertyRowMapper(TaxId.class));
	}

	@Override
	public List<TaxId> getTaxIdGroupedDetails(Integer policyId) {
		String query = """
                SELECT
                    MIN(tax_id_key) AS tax_id_key,
                    policy_id_fk,
                    client_group_id,
                    MIN(lob) as lob,
                    tax_id as tax_id,
                    STRING_AGG(claim_type, ', ') AS claim_type,
                    MIN(deleted_b) AS deleted_b,
                    MIN(created_date) AS created_date,
                    MIN(updated_date) AS updated_date
                FROM policy.client_tin_exclusions where policy_id_fk=:policyId
                GROUP BY policy_id_fk, client_group_id, lob, tax_id,deleted_b order by deleted_b ASC;
                """;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		return ipuNamedParameterJdbcTemplate.query(query, namedParameters, new BeanPropertyRowMapper<>(TaxId.class));
	}

 // stage
	@Override
	public void deleteStageData(Integer policyId) {
		String Query = """
                 Delete from etl.client_tin_exclusions_stage where policy_id=:policyId
                """;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(Query, namedParameters);
	}

	@Override
	public void deleteDeltaData(Integer policyId) {
		String Query = """
                 Delete from etl.client_tin_exclusions_delta where policy_id=:policyId
                """;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(Query, namedParameters);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveAllStageDataToTarget(Integer policyId) {
		String saveQuery = """
                            INSERT INTO policy.client_tin_exclusions (policy_id_fk, client_group_id, tax_id, lob, claim_type, deleted_b, created_date, updated_date)
                                SELECT policy_id, client_group_id, tax_id, lob, claim_type, deleted_b, now(), now()
                                FROM etl.client_tin_exclusions_delta WHERE policy_id = :policyId and delta_action = 'ADD'
                """;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(saveQuery, namedParameters);
	}



	// delta report
	@Override
	public boolean generateDeltaReport(Integer policyId, String sharePointDeltaPath) {
		try {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("policyId", policyId);
			ipuNamedParameterJdbcTemplate.update(StageTaxIdDeltaQuery, namedParameters);
			List<TaxIdDeltaDTO> resultSet = ipuNamedParameterJdbcTemplate.query(TaxIdQuery,
					namedParameters, new BeanPropertyRowMapper<>(TaxIdDeltaDTO.class));
			String deltaFileName = "Delta_TaxId.xlsx";
			taxIdStageDeltaReportHelper.createXLS(resultSet, sharePointDeltaPath, deltaFileName);
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

			List<TaxIdDeltaDTO> resultSet = ipuNamedParameterJdbcTemplate.query(TaxIdExceptionsQuery,
					namedParameters, new BeanPropertyRowMapper<>(TaxIdDeltaDTO.class));

			if (!resultSet.isEmpty()) {
				String taxIdExceptionsFile = "Exceptions_TaxId.xlsx";
				taxIdStageDeltaReportHelper.createXLS(resultSet, sharePointDeltaPath, taxIdExceptionsFile);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
