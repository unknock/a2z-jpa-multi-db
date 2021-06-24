package com.a2z.demo.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "blogEntityManagerFactory",
        transactionManagerRef = "blogTransactionManager",
        basePackages = {"com.a2z.demo.blog.repo"}
)
public class BlogJpaConfig {
    @Autowired
    private Environment env;

    @Bean(name = "blogDataSource")
    @ConfigurationProperties(prefix = "ds1.spring.datasource.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "blogEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean blogEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("blogDataSource") DataSource dataSource) {

        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql", env.getProperty("ds1.spring.datasource.hikari.show-sql", Boolean.class, false));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("ds1.spring.datasource.hikari.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("ds1.spring.datasource.hikari.dialect"));

        return builder.dataSource(dataSource)
                .packages("com.a2z.demo.blog.entity")
                .persistenceUnit(env.getProperty("ds1.spring.datasource.hikari.persistence-unit", "DS1_PU"))
                .properties(properties)
                .build();
    }

    @Bean(name = "blogTransactionManager")
    public PlatformTransactionManager blogTransactionManager(
            @Qualifier("blogEntityManagerFactory") EntityManagerFactory
                    blogEntityManagerFactory) {
        return new JpaTransactionManager(blogEntityManagerFactory);
    }
}
