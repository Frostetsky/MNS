package com.example.marketplaceservice.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.marketplaceservice.repository")
@ConditionalOnProperty(prefix = "database", name = "in-memory", havingValue = "false")
public class DataBaseConfiguration {

    @Bean(name = "transactionManager")
    @RefreshScope
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManager);
        return jpaTransactionManager;
    }

    @Bean(name = "entityManagerFactory")
    @RefreshScope
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(BasicDataSource basicDataSource){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean local = new LocalContainerEntityManagerFactoryBean();
        local.setDataSource(basicDataSource);
        local.setPackagesToScan("com.example.marketplaceservice.model");
        local.setJpaVendorAdapter(vendorAdapter);
        return local;
    }

    @Bean(name = "dataSource")
    @RefreshScope
    public BasicDataSource getDataSource(Environment environment){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getProperty("spring.data.driver"));
        basicDataSource.setUrl(environment.getProperty("spring.data.url"));
        basicDataSource.setPassword(environment.getProperty("spring.data.password"));
        basicDataSource.setUsername(environment.getProperty("spring.data.username"));
        return basicDataSource;
    }
}
