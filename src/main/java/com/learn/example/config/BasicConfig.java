package com.learn.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/*
** config data source , entity manager here
*/

@Configuration
@EnableJpaRepositories( basePackages = {"com.learn.example.repository" })
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.learn.example.service", "com.learn.example.model","com.learn.example.repository"/*, "com.learn.example.controller"*/})
public class BasicConfig {
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_data?useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("nextdefault");

		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(new String[]{"com.learn.example.model"});
		factory.setDataSource(dataSource());
		factory.setJpaProperties(jpaProperties());

		return factory;
	}
	
	private Properties jpaProperties() {  
	        Properties properties = new Properties();  
	        
	        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");  // MySQL5InnoDBDialect   MySQLMyISAMDialect
	        properties.put("hibernate.show_sql", "true");  
	        return properties;  
	}  
	@Bean
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return txManager;
	}
	
	
}