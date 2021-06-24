package com.a2z.demo.customer.config;


import com.a2z.demo.config.A2zDSConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ds2.spring.datasource.hikari")
public class CustomerDSProperties implements A2zDSConfig {

    private String jdbcUrl;

    private String username;

    private String password;

    private String driverClassName;

    private Integer maximumPoolSize;

    private Integer connectionTimeout;

    private Integer idleTimeout;

    private Integer minimumIdle;

    private Integer maxLifetime;

    private Boolean autoCommit;

    private Boolean allowPoolSuspension;

    private String dialect;

    private String ddlAuto;

    private String poolName;

    private String persistenceUnit;

    private Boolean secondLevelCache;

    private boolean showSql;

    private String connectionTestQuery;
}
