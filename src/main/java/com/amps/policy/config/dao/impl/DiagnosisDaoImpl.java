package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.DiagnosisDao;
import com.amps.policy.config.dto.DiagnosisDTO;
import com.amps.policy.config.model.Diagnosis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import static com.amps.policy.config.constants.ParameterConstants.Id;

import java.util.List;

@Component
public class DiagnosisDaoImpl implements DiagnosisDao {

	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	@Override
	public List<DiagnosisDTO> findByPolicyId(int policyId) {
		String findQuery = """
				select dx.* , action as policyCptActionKey, exclusion_b as exclusion, header_level_b as headerLevel,
				principal_dx_b as principalDx from policy.policy_dx as dx
				where policy_id =:policyId
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		return ipuNamedParameterJdbcTemplate.query(findQuery, namedParameters,
				new BeanPropertyRowMapper<>(DiagnosisDTO.class));
	}

	@Override
	public void saveData(List<DiagnosisDTO> DiagnosisDTO) {
		String query = """
				INSERT INTO policy.policy_dx(policy_id, diag_from, diag_to, dos_from, dos_to, action, exclusion_b,
				header_level_b, principal_dx_b, only_dx_b) 
				VALUES ( :policyId, :diagFrom, :diagTo, :dosFrom, :dosTo, :action, :exclusion, :headerLevel, :principalDx, :onlyDx)
				""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(DiagnosisDTO.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(query, params);
	}

	@Override
	public void deleteData(int id) {
		String deleteQuery = "delete from policy.policy_dx where policy_diagnosis_key=:id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue(Id, id);
		ipuNamedParameterJdbcTemplate.update(deleteQuery, namedParameters);
	}

	@Override
	public void deleteDxData(int id) {
		String deleteQuery = "delete from policy.policy_dx where policy_id=:id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue(Id, id);
		ipuNamedParameterJdbcTemplate.update(deleteQuery, namedParameters);
	}

	@Override
	public void updateData(Diagnosis diagnosis) {
		String updateQuery = """
		update policy.policy_dx set diag_from =:diagFrom, diag_to =:diagTo,
		dos_from=:dosFrom,dos_to =:dosTo,action=:action,exclusion_b=:exclusion,
		header_level_b=:headerLevel,principal_dx_b=:principalDx, 
		only_dx_b=:onlyDx where policy_diagnosis_key =:policyDiagnosisKey
		""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(diagnosis);
		ipuNamedParameterJdbcTemplate.batchUpdate(updateQuery, params);
	}

	@Override
	public void updateDiagHeaders(Diagnosis diagnosis) {
		String updateQuery = """
				update policy.policy_dx set principal_dx_b=:principalDx, only_dx_b=:onlyDx where policy_id =:policyId
				""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(diagnosis);
		ipuNamedParameterJdbcTemplate.batchUpdate(updateQuery, params);
	}

	@Override
	public void saveDiagsData(List<Diagnosis> diagnosis) {
		// TODO Auto-generated method stub
		String query = """
    INSERT INTO policy.policy_dx(policy_id,diag_from,diag_to,dos_from,dos_to,action,exclusion_b,header_level_b,principal_dx_b)
	VALUES (:policyId,:diagFrom,:diagTo,:dosFrom,:dosTo,:action,:exclusion,:headerLevel,:principalDx)
	""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(diagnosis.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(query, params);
	}

	@Override
	public List<DiagnosisDTO> getDxExportById(int id) {
		// TODO Auto-generated method stub
		return findByPolicyId(id);
	}
}
