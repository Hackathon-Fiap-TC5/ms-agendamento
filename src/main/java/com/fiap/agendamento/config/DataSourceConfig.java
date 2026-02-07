package com.fiap.agendamento.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        // Set minimum idle to 0 so it doesn't try to connect on startup
        dataSource.setMinimumIdle(0);
        // Don't validate connection on startup - allow app to start even if DB is unavailable
        dataSource.setInitializationFailTimeout(-1);
        // Set connection timeout to fail fast if DB is unreachable
        dataSource.setConnectionTimeout(10000);
        return dataSource;
    }
}
