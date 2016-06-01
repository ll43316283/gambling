package com.math040.gambling.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration  
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.math040.gambling.repository")
public class JpaConfig {
	  @Bean
	  public PlatformTransactionManager transactionManager()
	  {
		  JpaTransactionManager transaction = new JpaTransactionManager(); 
		  transaction.setEntityManagerFactory(entityManagerFactory().getObject()); 
	    return   transaction;
	  }
	  
	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	  {
	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(Boolean.TRUE);
	    vendorAdapter.setShowSql(Boolean.TRUE); 
	    factory.setDataSource(dataSource());
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan("com.math040.gambling.vo");
	    Properties jpaProperties = new Properties();
	    jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect"); 
	    factory.setJpaProperties(jpaProperties);
	    factory.afterPropertiesSet(); 
	    return factory;
	  }
	  
	  @Bean
	  public DataSource dataSource()
	  { 
	    return JdbcConnectionPool.create("jdbc:h2:file:D:/h2/gambling;MODE=Oracle;TRACE_LEVEL_SYSTEM_OUT=2","liang","test");
	  }
}
