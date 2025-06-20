package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.ClientGroupExclusionsDao;
import com.amps.policy.config.dto.ClientGroupExclusionDTO;
import com.amps.policy.config.dto.ClientPolicyDTO;
import com.amps.policy.config.dto.ClientPolicyExclusionsDTO;
import com.amps.policy.config.dto.ClientgroupDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ClientGroupExclusionsDaoImpl implements ClientGroupExclusionsDao {

    private static final Logger logger = LogManager.getLogger(ClientGroupExclusionsDaoImpl.class);

    @Autowired
    NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate dragonNamedParameterJdbcTemplate;

    @Override
    public List<ClientPolicyDTO> getPolicyData() {
        String Query = """
        select policy_number,policy_version,policy_desc,policy_id,
        medical_policy_key_fk,sub_policy_key_fk,reason_code_fk,category_fk from policy.policy
        """;
        return ipuNamedParameterJdbcTemplate.query(Query,
                new BeanPropertyRowMapper<>(ClientPolicyDTO.class));
    }

    @Override
    public List<ClientgroupDTO> getExcluisonData() {
        String Query = """
        select ct.code as client_code,cg.code as client_group_code,cg.name as client_group_name,
        cg.id as client_group_id,ct.name as client_name
        from public.client ct join public.client_group cg on ct.id = cg.client_id
        where client_id in (select id from public.client where id = id)
        """;
        return  dragonNamedParameterJdbcTemplate.query(Query,
                new BeanPropertyRowMapper<>(ClientgroupDTO.class));
    }

    @Override
    public List<ClientPolicyExclusionsDTO> getPolicyExclusionData() {
        String Query = """
                SELECT policy.policy_id,  policy.policy_version, policy.policy_number,policy.medical_policy_key_fk,
                policy.sub_policy_key_fk, client_group_policy_exclusion.client_group_id,
                policy.policy_number from Policy.policy
                INNER JOIN clients.client_group_policy_exclusion ON policy.policy_id = client_group_policy_exclusion.policy_id
                """;
       return ipuNamedParameterJdbcTemplate.query(Query,
                new BeanPropertyRowMapper<>(ClientPolicyExclusionsDTO.class));
    }

    @Override
    public List<ClientGroupExclusionDTO> postClientPolicyExclusionData(List<ClientGroupExclusionDTO> ClientGroupExclusionDTO) {
        String saveQuery = """
                insert into clients.client_group_policy_exclusion(client_group_id, policy_id)
                VALUES (:clientGroupId,:policyId)
                """;
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(ClientGroupExclusionDTO.toArray());
        ipuNamedParameterJdbcTemplate.batchUpdate(saveQuery, params);
        return ClientGroupExclusionDTO;
    }

    @Override
    public List<ClientGroupExclusionDTO> DeleteClientPolicyExclusionData(List<ClientGroupExclusionDTO> policyId) {
        String saveQuery = """
        DELETE from clients.client_group_policy_exclusion WHERE policy_id IN (:policyId)
        and client_group_id in (:clientGroupId)
        """;
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(policyId.toArray());
        ipuNamedParameterJdbcTemplate.batchUpdate(saveQuery, params);
        return policyId;
    }
}
