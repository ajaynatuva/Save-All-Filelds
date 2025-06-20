package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.ClientGroupProductDao;
import com.amps.policy.config.dto.ClientAssignmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientGroupProductDaoImpl implements ClientGroupProductDao {

	@Autowired
	NamedParameterJdbcTemplate dragonNamedParameterJdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

	@Override
	public List<ClientAssignmentDTO> getClientGroupProductData() {
		String query = """
				select cg.id as client_group_id,'01-01-1990'::DATE as client_start_date,coalesce(cgp.end_date,'12-31-9999') as client_end_date,
				cg.client_group_type_id=4 as hp from client_group cg left join client_group_policy cgp on
				cgp.client_group_id=cg.id left join client_group_product cgpr on cgpr.client_group_id=cg.id
				where cg.active=true and cgpr.product_id=35 and coalesce(cgp.pricing_runout_end_date,'12-31-9999') > now()
				and cg.client_id is not null and cg.client_group_type_id !=4
				""";
		return dragonNamedParameterJdbcTemplate.query(query, new BeanPropertyRowMapper<>(ClientAssignmentDTO.class));
	}

	@Override
	public List<ClientAssignmentDTO> getClientAssignmentData() {
		String query = """
		select * from policy.client_assignment
		""";
		return ipuNamedParameterJdbcTemplate.query(query, new BeanPropertyRowMapper<>(ClientAssignmentDTO.class));
	}
}
