package com.ain.engine.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
public class DatasourceProperties {

    @Bean(name = "csosDataSource")
    @Qualifier("csosDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.csos")
    public DataSource csosDataSource() { return DataSourceBuilder.create().build(); }

    @Bean(name = "adminDataSource")
    @Qualifier("adminDataSource")
    @ConfigurationProperties(prefix="spring.datasource.admin")
    public DataSource adminDataSource() {
        return DataSourceBuilder.create().build();
    }
}