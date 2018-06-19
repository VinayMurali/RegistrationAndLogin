package com.sakha.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.sakha.spring.model.Userdetails;

@Configuration
@PropertySource({ "classpath:application.properties" })
public class JDBCConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.sakha.spring" });
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		return dataSource;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}
	
	//added when got exe
//	@Autowired
//	@Bean(name = "sessionFactory")
//	public SessionFactory getSessionFactory(DataSource dataSource) {
//	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//	    sessionBuilder.addProperties(hibernateProperties());
//	    sessionBuilder.addAnnotatedClasses(Userdetails.class);
////	    sessionBuilder.addAnnotatedClasses(Bar.class);
////	    sessionBuilder.addAnnotatedClasses(Bat.class);
//	    return sessionBuilder.buildSessionFactory();
//	}
	
	Properties hibernateProperties() {
	      return new Properties() {
	         /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
	            setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
	            setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
	            setProperty("hibernate.globally_quoted_identifiers", "true");
	            setProperty("hibernate.show_sql", env.getProperty("hiberante.show_sql"));
	         }
	      };
	   }
}
