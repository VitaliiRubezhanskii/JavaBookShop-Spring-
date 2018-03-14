package ua.rubezhanskii.javabookshop.WebConfig;


import com.mysql.jdbc.Driver;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ua.rubezhanskii.javabookshop.datamanagement.jdbc.*;

import javax.sql.DataSource;


@Configuration
@ComponentScan("ua.rubezhanskii.javabookshop")

public class RootApplicationContextConfig {

    @Autowired
    private Environment environment;


    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource());
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(){
        return new SimpleJdbcInsert(dataSource());
    }
    @Bean
    public CategoryJdbcTemplate categoryJdbcTemplate(){
       return new CategoryJdbcTemplate();
    }


    @Bean
    public CustomerJdbcTemplate customerJdbcTemplate(){return new CustomerJdbcTemplate();}

    @Bean
    public CartJdbcTemplate cartJdbcTemplate(){
        return new CartJdbcTemplate();
    }

    @Bean
    public OrderJdbcTemplate orderJdbcTemplate(){return new OrderJdbcTemplate();}

    @Bean
    public BookJdbcTemplate bookJdbcTemplate(){
        return new BookJdbcTemplate();
    }



    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/javazone");
        dataSource.setPassword("qaz123");
        dataSource.setUsername("root");
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setMaxTotal(5);
        dataSource.setInitialSize(3);
        return dataSource;
    }

  /*  @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean=new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("ua.rubezhanskii.javabookshop");
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(properties());
        return factoryBean;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        vendorAdapter.setShowSql(true);
        return vendorAdapter;

    }
    @Bean
    public Properties properties(){
        Properties props=new Properties();
        props.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        return props;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }*/
}
