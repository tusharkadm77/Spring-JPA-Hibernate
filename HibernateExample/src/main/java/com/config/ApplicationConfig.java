package com.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;

@Component
@ComponentScan({"com"})
@EnableJpaRepositories({"com.repository"})
public class ApplicationConfig {

	//1. create dataSource.
	//2.
	
	/**
	 * Configure JDBC driverManager.
	 * 
	 * @return
	 */
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/synerzip");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		return dataSource;
	}

	/**
	 * Configure EntityManger.
	 * 
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean
				.setPackagesToScan(new String[] { "com.entity" });
		localContainerEntityManagerFactoryBean
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect",
				"org.hibernate.dialect.MySQLInnoDBDialect");
		jpaProperties.setProperty("hibernate.ejb.naming_strategy",
				"org.hibernate.cfg.ImprovedNamingStrategy");
		jpaProperties.setProperty("hibernate.show_sql", "true");
		
		/*
		 * jpaProperties.setProperty("hibernate.cache.use_second_level_cache",
		 * "true");
		 * jpaProperties.setProperty("hibernate.cache.use_second_level_cache",
		 * "org.hibernate.cache.EhCacheProvider");
		 */

		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
}
