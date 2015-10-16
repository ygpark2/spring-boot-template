
package com.ain.engine.config;

import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryOther",
        transactionManagerRef = "transactionManagerOther",
        basePackages = { "com.ain.engine.repository.admin" }
)
public class AdminRepositoryConfig {
    @Inject
    private JpaVendorAdapter jpaVendorAdapter;

    @Inject
    private JpaProperties jpaProperties;

    @Inject @Qualifier("adminDataSource")
    private DataSource adminDataSource;

    @Bean(name = "entityManagerOther")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryOther(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryOther")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryOther (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(adminDataSource)
                .properties(getVendorProperties(adminDataSource))
                .packages("com.ain.engine.domain.admin")
                .persistenceUnit("adminPersistenceUnit")
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerOther")
    PlatformTransactionManager transactionManagerOther(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryOther(builder).getObject());
    }
}
