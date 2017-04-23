package com.template.springMVCtemplate.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ud on 23/4/17.
 *
 * @Configuration : It ask spring to treat this class as a Configuration Class.
 * @EnableTransactionManagement  Enables Spring's annotation-driven transaction management capability.
 *                               It searches for the bean annotated by @Transactional and tells spring
 *                               when to begin a db transaction.
 * @ComponentScan : It is the basis of Annotation driven java application.It scans the given package and finds the
 * various annotation which creates the beans , applies constraints,maps objects etc.
 *
 * @PropertySource :It initializes the environment variable with the information provided in
 * application.properties file.Generally this file contains info about database connection,schema creation rules etc.
 *
 *
 */

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.template.springMVCtemplate.configuration"})
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfig {

    //Initializes environment variable of spring which will contain application specific info given in application.properties file
    @Autowired
    private Environment environment;

    /**
     * Creates a datasource object which will contains all the database info and credential.It will be used by
     * session factory to create sessions with the database.
     *
     * @return a DataSource Object.
     */
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    /**
     * Given hibernates custom properties that will be used during database transaction.
     * @return property object.
     */

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    /**
     * This is the place where the session factory object get created using the datasource object and hibernate
     * properties.This bean is autowired in the DAO (here : AbstractDao).Session Factory objects are used to
     * created sessions for various transaction.
     *
     * @return SessionFactory Bean
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean localSessionFactoryBean=new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan(new String[] { "com.template.springMVCtemplate.model" });
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        return localSessionFactoryBean;
    }

    /**
     * This is Springs transaction manager. It manages the whole transaction lifecycle form initiation to commit.
     * @param s
     * @return
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

}
