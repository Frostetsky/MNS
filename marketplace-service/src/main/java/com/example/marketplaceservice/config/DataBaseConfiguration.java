package com.example.marketplaceservice.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.marketplaceservice.repository")
public class DataBaseConfiguration {

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManager);
        return jpaTransactionManager;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(BasicDataSource basicDataSource){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        LocalContainerEntityManagerFactoryBean local = new LocalContainerEntityManagerFactoryBean();
        local.setDataSource(basicDataSource);
        local.setPackagesToScan("com.example.marketplaceservice.model");
        local.setJpaVendorAdapter(vendorAdapter);
        return local;
    }

    @Bean
    public BasicDataSource getData(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        basicDataSource.setPassword("password");
        basicDataSource.setUsername("postgres");
        return basicDataSource;
    }
}
