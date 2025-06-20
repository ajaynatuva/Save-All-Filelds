package com.amps.policy.config.dao.impl;

import static com.amps.policy.config.constants.ParameterConstants.Id;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.amps.policy.config.dao.ClientAssignmentDao;
import com.amps.policy.config.dto.ClientAssignmentDTO;
import com.amps.policy.config.dto.ClientAssignmentDTO1;
import com.amps.policy.config.dto.copyClientDTO;
import com.amps.policy.config.model.ClientAssignment;

@Component
public class ClientAssignmentDaoImpl implements ClientAssignmentDao {

	@Autowired
	@Qualifier("ipuNamedParameterJdbcTemplate")
	NamedParameterJdbcTemplate ipNamedParameterJdbcTemplate;

	@Autowired
	@Qualifier("dragonNamedParameterJdbcTemplate")
	NamedParameterJdbcTemplate dragonNamedParameterJdbcTemplate;

	@Override
	public void postClientAssignmentData1(List<ClientAssignmentDTO> clientAssignmentData) {

		// TODO Auto-generated method stub
		String Query = """
				insert into policy.client_assignment (policy_id, client_group_id,
				client_start_date, client_end_date, exclude_client_specific_codes, hp, create_date, update_date) values
				(:policyId, :clientGroupId, :clientStartDate,:clientEndDate,:excludeClientSpecificCodes,:hp, 'now()', 'now()')
				""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(clientAssignmentData.toArray());
		ipNamedParameterJdbcTemplate.batchUpdate(Query, params);
	}

	@Override
	public List<ClientAssignmentDTO> getActiveClientGroups() {
		String Query = """
				SELECT  cl.code as clientCode,cl.name as clientName,
				cg.code as clientGroupCode,cg.id as clientGroupId,
				cg.name as client_group_name,cg.client_group_type_id = 4 as hp,
				true AS excludeClientSpecificCodes,clp.client_id as clientId,
				pr.product_id as productId,pr.end_date as endDate FROM
				public.client_product clp INNER JOIN
				public.client_group_product pr ON pr.product_id = clp.id INNER JOIN
				public.client_group cg ON pr.client_group_id = cg.id INNER JOIN
				public.client_group_type cgt on cg.client_group_type_id = cgt.id Inner Join
				   public.client cl ON cg.client_id = cl.id WHERE pr.product_id = 35  order by cg.client_id
				""";
		return dragonNamedParameterJdbcTemplate.query(Query,
				new BeanPropertyRowMapper<>(ClientAssignmentDTO.class));
	}

	@Override
	public void updateClientAssignmentData(ClientAssignment clientAssignmentData) {
		String Query = """
				UPDATE policy.client_assignment SET client_start_date=:clientStartDate,client_end_date=:clientEndDate,
				exclude_client_specific_codes=:excludeClientSpecificCodes, update_date=now()
				WHERE policy_clnt_assmt_key =:policyClntAssmtKey
				""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(clientAssignmentData);
		ipNamedParameterJdbcTemplate.batchUpdate(Query, params);
	}

	@Override
	public void updateClientAssignmentData1(ClientAssignmentDTO clientAssignmentData) {
		String Query = """
				UPDATE policy.client_assignment SET client_start_date=:clientStartDate,client_end_date=:clientEndDate,
				exclude_client_specific_codes=:excludeClientSpecificCodes, update_date=now()
				WHERE policy_clnt_assmt_key =:policyClntAssmtKey
				""";
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(clientAssignmentData);
		ipNamedParameterJdbcTemplate.batchUpdate(Query, params);
	}

	@Override
	public void deleteClientAssignmentData(int id) {
		String deleteQuery = "DELETE FROM policy.client_assignment WHERE policy_clnt_assmt_key=:id;";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue(Id, id);
		ipNamedParameterJdbcTemplate.update(deleteQuery, namedParameters);
	}

	@Override
	public List<ClientAssignmentDTO1> getClientAssignmentData(int policyId) {
		String getQuery = """
				select * FROM policy.client_assignment WHERE policy_id=:policyId ORDER BY client_start_date ASC,
				client_end_date DESC;
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("policyId", policyId);
		return ipNamedParameterJdbcTemplate.query(getQuery, namedParameters,
				new BeanPropertyRowMapper<>(ClientAssignmentDTO1.class));
	}

	@Override
	public List<copyClientDTO> getCopyClientAssignmentData() {
		// TODO Auto-generated method stub
		String getQuery = """

				 SELECT DISTINCT ON (c.client_group_id)
						  c.client_group_id AS clientGroupId,
						  c.policy_id AS policyId,
						  c.client_start_date,
						  c.client_end_date
					  FROM
						  policy.client_assignment c
					  LEFT JOIN
						  policy.policy p
					  ON
						  p.policy_id = c.policy_id AND p.is_prod_b = 1;
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		return ipNamedParameterJdbcTemplate.query(getQuery, namedParameters,
				new BeanPropertyRowMapper<>(copyClientDTO.class));
	}

	@Override
	public List<ClientAssignmentDTO> getActiveClientGroupsNotHp() {
		// TODO Auto-generated method stub
				// considering end date in cgp pricing end end date if it is null considering cgp end date if it is also null
				// checking in clientgroupproduct and considering enddate if record found but date is null
				// making maxium end date
				String Query = """
							SELECT
						    cl.code AS clientCode,
						    cl.name AS clientName,
						    cg.code AS clientGroupCode,
						    cg.id AS clientGroupId,
						    cg.name AS client_group_name,
						    false AS hp,
						    true AS excludeClientSpecificCodes,
						    clp.client_id AS clientId,
						    pr.product_id AS productId,
							  COALESCE(
						    cgp.pricing_runout_end_date,
						    cgp.end_date,
						    pr.end_date,
						    '9999-12-31'
						) AS clientEndDate

						FROM
						    public.client_product clp
						INNER JOIN
						    public.client_group_product pr ON pr.product_id = clp.id
						INNER JOIN
						    public.client_group cg ON pr.client_group_id = cg.id

						LEFT JOIN
						public.client_group_policy cgp on cg.id = cgp.client_group_id

						INNER JOIN
						    public.client_group_type cgt ON cg.client_group_type_id = cgt.id
						INNER JOIN
						    public.client cl ON cg.client_id = cl.id

						WHERE
						    pr.product_id = 35
						    AND cg.client_group_type_id != 4
							AND cg.active = true
							AND  COALESCE(
						    cgp.pricing_runout_end_date,
						    cgp.end_date,
						    pr.end_date,
						    '9999-12-31'
						) > now()
						ORDER BY
						    cg.client_id
						""";
				return dragonNamedParameterJdbcTemplate.query(Query,
						new BeanPropertyRowMapper<>(ClientAssignmentDTO.class));
	}

	public List<copyClientDTO> getPolicyIds(List<copyClientDTO> clientGroupIds, List<copyClientDTO> clientGroupIds1)
			throws ParseException {

		List<Integer> clientGroupId = new ArrayList<>();
		List<Integer> clientGroupId1 = new ArrayList<>();
		List<Integer> policyId = new ArrayList<>();
		List<copyClientDTO> result1;
		List<copyClientDTO> clientDTO = new ArrayList<copyClientDTO>();
		List<copyClientDTO> originalClientGroupData = !clientGroupIds.isEmpty() ? clientGroupIds : clientGroupIds1;
		for (copyClientDTO dto : originalClientGroupData) {
			Integer numbers = dto.getClientGroupId();
			clientGroupId.add(numbers);
		}
		clientGroupId = originalClientGroupData.stream().map(copyClientDTO:: getClientGroupId).collect(Collectors.toList());
		String getQuery = """
				select distinct policy_id from policy.client_assignment where client_group_id in (:clientGroupId)
				""";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("clientGroupId", clientGroupId);
		List<copyClientDTO> result = ipNamedParameterJdbcTemplate.query(getQuery, namedParameters,
				new BeanPropertyRowMapper<>(copyClientDTO.class));

		if (result.isEmpty()) {
			copyClientDTO dto = new copyClientDTO();
			dto.setType("newClient");
		    clientDTO.add(dto);
			return clientDTO;
		}

		policyId = result.stream().map(copyClientDTO :: getPolicyId).collect(Collectors.toList());
		String clientStartDateDTO = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (copyClientDTO dto1 : clientGroupIds1) {
			Integer cg1 = dto1.getClientGroupId();
			clientStartDateDTO = dto1.getClientStartDate();
			clientGroupId1.add(cg1);
		}
		Date startDate = sdf.parse(clientStartDateDTO);

		String getQuery1 = """
				SELECT
						client_group_id,
						policy_id,
						client_start_date,
						client_end_date,
						'newClient' AS type
						FROM
						policy.client_assignment
						WHERE
						policy_id IN (:policyId)
						AND client_group_id IN (:clientGroupId)
						AND (:clientStartDate) BETWEEN client_start_date AND client_end_date;
				""";
		MapSqlParameterSource namedParameters1 = new MapSqlParameterSource();
		namedParameters
				.addValue("policyId", policyId)
				.addValue("clientGroupId", clientGroupId1)
				.addValue("clientStartDate", startDate);
		result1 = ipNamedParameterJdbcTemplate.query(getQuery1, namedParameters,
				new BeanPropertyRowMapper<>(copyClientDTO.class));
		if (!clientGroupIds.isEmpty()) {
			result1.stream().map(copyClientDTO::getPolicyId).forEach(policyId::remove);
			String query1 = null;
			if (!policyId.isEmpty()) {
				query1 = """
						select DISTINCT policy_id,'newClient' AS type from policy.policy where policy_id NOT IN(:policyId)
						""";
			}
			if (policyId.isEmpty()) {
				query1 = """
						select DISTINCT policy_id,'newClient' AS type from policy.policy where policy_id is NOT NULL;
						""";
			}
			namedParameters.addValue("policyId", policyId);
			result1 = ipNamedParameterJdbcTemplate.query(query1, namedParameters,
					new BeanPropertyRowMapper<>(copyClientDTO.class));
		}
		return result1;
	}
}
