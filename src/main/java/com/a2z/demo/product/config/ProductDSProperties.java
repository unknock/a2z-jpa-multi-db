package com.a2z.demo.product.config;


import com.a2z.demo.config.A2zDSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
//@Configuration(value = "productHikariProperties")
//@Component(value = "productHikariProperties")
public class ProductDSProperties implements A2zDSConfig {
    @Autowired
    private Environment env;

    @Override
    public String getJdbcUrl() {
        return env.getProperty("ds3.spring.datasource.hikari.jdbc-url");
    }

    @Override
    public String getUsername() {
        return env.getProperty("ds3.spring.datasource.hikari.username");
    }

    @Override
    public String getPassword() {
        return env.getProperty("ds3.spring.datasource.hikari.password");
    }

    @Override
    public String getDriverClassName() {
        return env.getProperty("ds3.spring.datasource.driver-class-name");
    }

    @Override
    public Integer getMaximumPoolSize() {
        return env.getProperty("ds3.spring.datasource.hikari.maximum-pool-size", Integer.class, 10);
    }

    @Override
    public Integer getConnectionTimeout() {
        return env.getProperty("ds3.spring.datasource.hikari.connection-timeout", Integer.class, 10);
    }

    @Override
    public Integer getIdleTimeout() {
        return env.getProperty("ds3.spring.datasource.hikari.idle-timeout", Integer.class, 10000);
    }

    @Override
    public Integer getMinimumIdle() {
        return env.getProperty("ds3.spring.datasource.hikari.minimum-idle", Integer.class, 3);
    }

    @Override
    public Integer getMaxLifetime() {
        return env.getProperty("ds3.spring.datasource.hikari.max-lifetime", Integer.class, 1000);
    }

    @Override
    public Boolean getAutoCommit() {
        return env.getProperty("ds3.spring.datasource.hikari.auto-commit", Boolean.class, true);
    }

    @Override
    public Boolean getAllowPoolSuspension() {
        return env.getProperty("ds3.spring.datasource.hikari.allow-pool-suspension", Boolean.class, true);
    }

    @Override
    public String getDialect() {
        return env.getProperty("ds3.spring.jpa.hibernate.dialect");
    }

    @Override
    public String getDdlAuto() {
        return env.getProperty("ds3.spring.jpa.hibernate.ddl-auto");
    }

    @Override
    public String getPoolName() {
        return env.getProperty("ds3.spring.datasource.hikari.pool-name");
    }

    @Override
    public String getPersistenceUnit() {
        return env.getProperty("ds3.spring.datasource.hikari.persistence-unit");
    }

    @Override
    public Boolean getSecondLevelCache() {
        return env.getProperty("ds3.spring.datasource.hikari.second-level-cache", Boolean.class, true);
    }

    @Override
    public boolean isShowSql() {
        return env.getProperty("ds3.spring.datasource.hikari.show-sql", Boolean.class, false);
    }

    @Override
    public String getConnectionTestQuery() {
        return env.getProperty("ds3.spring.datasource.hikari.connection-test-query", "SELECT 1");
    }
}
