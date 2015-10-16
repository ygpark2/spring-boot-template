package com.ain.engine.config;

// import org.apache.commons.dbcp.BasicDataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="com.ain.engine.repository.mapper")
public class MyBatisConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        System.out.println("==========> data source loaded <===========");
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println("==========> Sql Session Factory <===========");
        PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        // sessionFactory.setConfigLocation(pmrpr.getResource("classpath:mybatis-config.xml"));
        sessionFactory.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
        sessionFactory.setMapperLocations(pmrpr.getResources("classpath:com/ain/engine/repository/mapper/*.xml"));
        // sessionFactory.setTypeAliasesPackage("com.kdn.csos.engine.repository.entity");
        return sessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        System.out.println("==========> DataSourceTransactionManager <===========");
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSession sqlSessionForMyBatis(SqlSessionFactory sqlSessionFactory) {
        System.out.println("==========> sqlSessionForMyBatis <===========");
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}