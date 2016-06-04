package com.math040.gambling.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

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
	    //jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect"); 
	    jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"); 
	    factory.setJpaProperties(jpaProperties);
	    factory.afterPropertiesSet(); 
	    return factory;
	  }
	  
	  @Bean
	  public DataSource dataSource() 
	  { 
	    //return JdbcConnectionPool.create("jdbc:h2:file:D:/h2/gambling;MODE=Oracle;TRACE_LEVEL_SYSTEM_OUT=2","liang","test");
		  ComboPooledDataSource dataSource = new ComboPooledDataSource();
		  try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		  } catch (PropertyVetoException e) { 
		  }
		  dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/gambling?characterEncoding=UTF-8");
		  dataSource.setUser("root");
		  dataSource.setPassword("admin");
		  return dataSource;
	  }
}
