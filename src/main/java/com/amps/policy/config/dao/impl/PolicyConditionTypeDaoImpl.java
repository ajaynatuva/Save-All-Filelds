package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.PolicyConditionTypeDao;
import com.amps.policy.config.model.ConditionCodeActionLkp;
import com.amps.policy.config.model.PolicyConditionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PolicyConditionTypeDaoImpl implements PolicyConditionTypeDao {

	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	@Override
	public List<ConditionCodeActionLkp> getConditionData() {
		// TODO Auto-generated method stub
		String Query = """
		SELECT * FROM policy.policy_cond_code_action_lkp
		""";
		return ipuNamedParameterJdbcTemplate.query(Query,
				new BeanPropertyRowMapper<>(ConditionCodeActionLkp.class));
	}

	@Override
	public List<PolicyConditionCode> getPolicyConditionData(Integer policyId) {
		// TODO Auto-generated method stub
		String Query = """
		SELECT * FROM policy.policy_condition_code where policy_id =:policyId
		""";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		return ipuNamedParameterJdbcTemplate.query(Query, namedParameters,
				new BeanPropertyRowMapper<>(PolicyConditionCode.class));
	}

	@Override
	public void PostConditionData(List<PolicyConditionCode> policyConditionTypes) {
		// TODO Auto-generated method stub
		
		String Query = """
		insert into policy.policy_condition_code (policy_id,condition_code,condition_code_desc)
		values(:policyId,:conditionCode,:conditionCodeDesc)
		""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(policyConditionTypes.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(Query, params);
	}

	@Override
	public void DeleteData(Integer key) {
		// TODO Auto-generated method stub
		String Query = """
		delete from policy.policy_condition_code where policy_condition_code_key =:key
		""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("key", key);
		ipuNamedParameterJdbcTemplate.update(Query, namedParameters);	
	}
}
