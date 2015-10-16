
package com.ain.engine.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryMain",
        transactionManagerRef = "transactionManagerMain",
        basePackages = { "com.ain.engine.repository.csos" }
)
public class CSOSRepositoryConfig {
    @Inject
    private JpaProperties jpaProperties;

    @Inject
    @Qualifier("csosDataSource")
    private DataSource csosDataSource;

    @Bean(name = "entityManagerMain")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryMain(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryMain")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMain (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(csosDataSource)
                .properties(getVendorProperties(csosDataSource))
                .packages("com.ain.engine.domain.csos")
                .persistenceUnit("csosPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerMain")
    @Primary
    PlatformTransactionManager transactionManagerMain(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryMain(builder).getObject());
    }
}