package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.TaxonomyDao;
import com.amps.policy.config.dto.TaxonomyDTO;
import com.amps.policy.config.dto.TaxonomyDeltaDTO;
import com.amps.policy.config.helper.TaxonomyStageDeltaReportHelper;
import com.amps.policy.config.model.Taxonomy;
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

import static com.amps.policy.config.queries.StageTaxonomyDeltaQuery.*;

@Component
public class TaxonomyStageDaoImpl implements TaxonomyDao {

	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;
	@Autowired
	TaxonomyStageDeltaReportHelper taxonomyStageDeltaReportHelper;
	@Autowired
	JdbcTemplate ipuJdbcTemplate;

	@Override
	@Transactional
	public void saveData(List<TaxonomyDTO> taxonomyDTO) {
		String Query = """
				INSERT INTO etl.taxonomy_stage(policy_id, client_group_id, taxonomy_code, spec_code, subspec_code, subspec_desc, function, deleted_b)
					VALUES (:policyId, :clientGroupId, :taxonomyCode, :specCode, :subSpecCode, :subSpecDesc, :function, :deletedB)
				""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(taxonomyDTO.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(Query, params);
	}

	@Override
	public void deleteStageData(Integer policyId) {
		String Query = """
				 Delete from etl.taxonomy_stage where policy_id=:policyId
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(Query, namedParameters);
	}

	@Override
	public void deleteDeltaData(Integer policyId) {
		String Query = """
				 Delete from etl.taxonomy_delta where policy_id=:policyId
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(Query, namedParameters);
	}
	@Override
	@Transactional
	public boolean generateDeltaReport(Integer policyId, String dropboxDeltaPath) {
		try {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("policyId", policyId);
			ipuNamedParameterJdbcTemplate.update(StageTaxonomyDeltaQuery, namedParameters);
			List<TaxonomyDeltaDTO> resultSet = ipuNamedParameterJdbcTemplate.query(TaxonomyQuery,
					namedParameters, new BeanPropertyRowMapper<>(TaxonomyDeltaDTO.class));
			String deltaFileName = "Delta_Taxonomy.xlsx";
			taxonomyStageDeltaReportHelper.createXLS(resultSet, dropboxDeltaPath, deltaFileName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean generateExceptionReport(Integer policyId, String dropboxExceptionPath) {
		try {
			// Define parameters for the query
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			namedParameters.addValue("policyId", policyId);

			// Execute the query and map results to a list of TaxonomyDeltaDTO
			List<TaxonomyDeltaDTO> resultSet = ipuNamedParameterJdbcTemplate.query(TaxnomyExceptionsQuery,
					namedParameters, new BeanPropertyRowMapper<>(TaxonomyDeltaDTO.class));

			// Check if the result set is not empty
			if (!resultSet.isEmpty()) {
				String taxonomyExceptionsFile = "Exceptions_Taxonomy.xlsx";
				taxonomyStageDeltaReportHelper.createXLS(resultSet, dropboxExceptionPath, taxonomyExceptionsFile);
				return true; // Return true after successful report generation
			}

			// If no valid action or empty result set, return false
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Return false if an exception occurs
		}
	}

	@Override
	public List<Taxonomy> getAllDeleteActionTaxonomyData(Integer policyId) {
		String getQuery = """
                    select subspec_code as subSpecCode, subspec_desc as subSpecDesc,* from etl.taxonomy_delta where policy_id = :policyId and delta_action = 'DELETE'
                """;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		return ipuNamedParameterJdbcTemplate.query(getQuery, namedParameters,
				new BeanPropertyRowMapper<>(Taxonomy.class));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteTaxonomyData(List<Taxonomy> taxonomyList) {
		String deleteQuery = """
                update policy.taxonomy set deleted_b = 1 ,updated_date =now() where taxonomy_code=:taxonomyCode and policy_id =:policyId
                and client_group_id = :clientGroupId and spec_code = :specCode and subspec_code = :subSpecCode
                """;
		SqlParameterSource[] deletedParams = SqlParameterSourceUtils.createBatch(taxonomyList);
		ipuNamedParameterJdbcTemplate.batchUpdate(deleteQuery, deletedParams);
	}


	@Override
	public void saveAllStageData(Integer policyId) {
		String saveQuery = """
			INSERT INTO policy.taxonomy(policy_id, client_group_id, taxonomy_code, spec_code, subspec_code, subspec_desc, function, deleted_b, created_date, updated_date)
				SELECT policy_id,client_group_id, taxonomy_code, spec_code, subspec_code, subspec_desc, function, deleted_b, now(), now()
				FROM etl.taxonomy_delta  where policy_id =:policyId and delta_action = 'ADD'
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		ipuNamedParameterJdbcTemplate.update(saveQuery, namedParameters);
	}
}
