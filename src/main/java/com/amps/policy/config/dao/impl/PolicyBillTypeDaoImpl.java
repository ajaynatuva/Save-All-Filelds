package com.amps.policy.config.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import com.amps.policy.config.dao.PolicyBillTypeDao;
import com.amps.policy.config.dto.PolicyBillTypeDTO;
import com.amps.policy.config.model.PolicyBillType;

@Component
public class PolicyBillTypeDaoImpl implements PolicyBillTypeDao {

	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	@Override
	public void savePolicyBillType(List<PolicyBillType> PolicyBillType) {
		// TODO Auto-generated method stub
		String query = """
				INSERT INTO policy.policy_bill_types(policy_id, bill_type, bill_type_desc)
					VALUES ( :policyId, :billType, :billTypeDesc)
				""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(PolicyBillType.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(query, params);
	}

	@Override
	public void deletePolicyBillType(int key) {
		String query = """
		delete from policy.policy_bill_types where policy_bill_type_key = :key
		""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("key", key);
		ipuNamedParameterJdbcTemplate.update(query, namedParameters);
	}

	public void deleteBillTypeByPolicyId(List<PolicyBillTypeDTO> policyId) {
		String query = """
		delete from policy.policy_bill_types where policy_id = :policyId and bill_type=:billType
		""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(policyId.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(query, params);
	}

	@Override
	public void saveData(List<PolicyBillTypeDTO> policyBillTypeDTO) {
		String query = """
		INSERT INTO policy.policy_bill_types(policy_id, bill_type, bill_type_desc) VALUES (:policyId,:billType,:billTypeDesc)
		""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(policyBillTypeDTO.toArray());
		ipuNamedParameterJdbcTemplate.batchUpdate(query, params);
	}
}
