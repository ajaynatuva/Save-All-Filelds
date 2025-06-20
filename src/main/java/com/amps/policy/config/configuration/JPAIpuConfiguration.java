package com.amps.policy.config.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * @author Anusha Kakarla
 *
 * 
 */
@Configuration
public class JPAIpuConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.ipu")
    HikariDataSource ipuDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
    
    @Bean
    JdbcTemplate ipuJdbcTemplate(@Qualifier("ipuDataSource") DataSource ipuDataSource){
		return new JdbcTemplate(ipuDataSource);
	}
    
    @Bean
    NamedParameterJdbcTemplate ipuNamedParameterJdbcTemplate(@Qualifier("ipuDataSource") DataSource ipuDataSource) {
    	return new NamedParameterJdbcTemplate(ipuDataSource);
    }
}