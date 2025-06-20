package com.amps.policy.config.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DragonJpaConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dragon")
    HikariDataSource dragonDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    JdbcTemplate dragonJdbcTemplate(@Qualifier("dragonDataSource") DataSource dragonDataSource){
        return new JdbcTemplate(dragonDataSource);
    }

    @Bean
    NamedParameterJdbcTemplate dragonNamedParameterJdbcTemplate(@Qualifier("dragonDataSource") DataSource dragonDataSource) {
        return new NamedParameterJdbcTemplate(dragonDataSource);
    }
}
