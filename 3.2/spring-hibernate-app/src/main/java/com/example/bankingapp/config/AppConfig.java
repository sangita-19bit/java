package com.example.bankingapp.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.example.bankingapp")
@EnableTransactionManagement
public class AppConfig {

    // Update credentials below for your DB
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/bankdb?useSSL=false&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("yourpassword");
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan("com.example.bankingapp.entity");
        sf.setHibernateProperties(hibernateProperties());
        return sf;
    }

    private Properties hibernateProperties() {
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.hbm2ddl.auto", "update"); // use 'update' for dev
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        // Important: when using Spring's HibernateTransactionManager with sessionFactory.getObject(),
        // you should enable current_session_context_class managed by Spring:
        props.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        return props;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setSessionFactory(sessionFactory);
        return tm;
    }
}
