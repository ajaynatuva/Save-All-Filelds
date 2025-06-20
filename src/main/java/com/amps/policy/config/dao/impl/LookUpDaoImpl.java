package com.amps.policy.config.dao.impl;

import com.amps.policy.config.dao.LookUpDao;
import com.amps.policy.config.dto.SubSpecDTO;
import com.amps.policy.config.model.ModLookUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class LookUpDaoImpl implements LookUpDao {
    @Autowired
    NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate;
    @Override
    public List<SubSpecDTO> getSubSpecData() {
        String query = "select * from policy.subspec_map";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        return  ipuNamedParameterJdbcTemplate.query(query, namedParameters, new BeanPropertyRowMapper<>(SubSpecDTO.class));
    }
    @Override
    public void updateDeviationsInCCI(Integer Key, String column_i, String column_ii) {
        String Query = """
                update source.cci set deviations=true 
                where cci_key=:cciKey and column_i=:column_i and column_ii=:column_ii
                """;
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("cciKey", Key)
                .addValue("column_i", column_i)
                .addValue("column_ii", column_ii);
        ipuNamedParameterJdbcTemplate.update(Query, namedParameters);
    }

    @Override
    public List<ModLookUp> getModLkpData() {
        String Query = """
        select * from source.mod_lkp order by cpt_mod asc
        """;
        return ipuNamedParameterJdbcTemplate.query(Query,new BeanPropertyRowMapper<>(ModLookUp.class));
    }
}
