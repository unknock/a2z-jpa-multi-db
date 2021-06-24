package com.a2z.demo.product.config;

import com.a2z.demo.config.A2zDSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Slf4j
@Configuration
//@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager",
        basePackages = {"com.a2z.demo.product.repo"}
)
public class ProductJpaConfig {
    @Autowired
    private A2zDSConfig productDSProperties;

    @Bean(name = "productDataSource")
    @ConfigurationProperties(prefix = "ds3.spring.datasource")
    public DataSource productDataSource() {
        return productDSProperties.getDataSource();
    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("productDataSource") DataSource dataSource) {

        return builder.dataSource(dataSource)
                .packages("com.a2z.demo.product.entity")
                .persistenceUnit(productDSProperties.getPersistenceUnit())
                .properties(productDSProperties.getExtraHibernateProperties())
                .build();
    }

    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager productTransactionManager(
            @Qualifier("productEntityManagerFactory") EntityManagerFactory
                    productEntityManagerFactory) {
        return new JpaTransactionManager(productEntityManagerFactory);
    }
}
