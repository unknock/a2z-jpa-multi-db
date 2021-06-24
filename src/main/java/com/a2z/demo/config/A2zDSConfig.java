package com.a2z.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * The A2z Hikari config API.
 */
public interface A2zDSConfig {
    /**
     * Gets jdbc url.
     *
     * @return the jdbc url
     */
    String getJdbcUrl();

    /**
     * Gets username.
     *
     * @return the username
     */
    String getUsername();

    /**
     * Gets password.
     *
     * @return the password
     */
    String getPassword();

    /**
     * Gets driver class name.
     *
     * @return the driver class name
     */
    String getDriverClassName();

    /**
     * Gets maximum pool size.
     *
     * @return the maximum pool size
     */
    Integer getMaximumPoolSize();

    /**
     * Gets maximum number of milliseconds that a client will wait for a connection.
     *
     * @return the connection timeout
     */
    Integer getConnectionTimeout();

    /**
     * Gets maximum idle time for connection.
     *
     * @return the connection timeout
     */
    Integer getIdleTimeout();

    /**
     * Gets minimum number of idle connections maintained by HikariCP in a connection pool.
     *
     * @return the minimum idle
     */
    Integer getMinimumIdle();

    /**
     * Get maximum lifetime in milliseconds of a connection in the pool after it is closed.
     *
     * @return max lifetime
     */
    Integer getMaxLifetime();

    /**
     * Gets auto commit.
     *
     * @return the auto commit
     */
    Boolean getAutoCommit();

    /**
     * Gets allow pool suspension.
     *
     * @return the allow pool suspension
     */
    Boolean getAllowPoolSuspension();

    /**
     * Gets hibernate dialect.
     *
     * @return the hibernate dialect
     */
    String getDialect();

    /**
     * Gets hibernate ddl auto.
     *
     * @return the hibernate ddl auto
     */
    String getDdlAuto();

    /**
     * Gets pool name.
     *
     * @return the pool name
     */
    String getPoolName();

    /**
     * Gets persistence unit.
     *
     * @return the persistence unit
     */
    String getPersistenceUnit();

    /**
     * Gets second level cache.
     *
     * @return the second level cache
     */
    Boolean getSecondLevelCache();


    /**
     * Is show sql boolean.
     * <p>
     * spring.jpa.show-sql=true|false
     *
     * @return the boolean
     */
    boolean isShowSql();

    /**
     * Gets connection test query.
     *
     * @return the connection test query
     */
    String getConnectionTestQuery();

    /**
     * Gets extra hibernate properties.
     *
     * @return the extra hibernate properties
     */
    default HashMap<String, Object> getExtraHibernateProperties() {
        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.show_sql", this.isShowSql());
        properties.put("hibernate.hbm2ddl.auto", this.getDdlAuto());
        properties.put("hibernate.dialect", this.getDialect());
        properties.put("hibernate.cache.use_second_level_cache", this.getSecondLevelCache());

        return properties;
    }

    /**
     * Gets data source.
     *
     * @return the data source
     */
    default DataSource getDataSource() {
        //final DataSource dataSource = DataSourceBuilder.create().build();

        /*return DataSourceBuilder.create()
                .username("postgres")
                .password("root2root").driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/productdb")
                .build();*/

        final HikariDataSource dataSource = DataSourceBuilder.create()
                .url(this.getJdbcUrl())
                .username(this.getUsername())
                .password(this.getPassword())
                .driverClassName(this.getDriverClassName())
                .type(HikariDataSource.class)
                .build();

        // https://blogs.ashrithgn.com/multiple-data-source-in-spring-boot-using-spring-boot-data-starter/
        dataSource.setMaximumPoolSize(this.getMaximumPoolSize());
        dataSource.setAllowPoolSuspension(this.getAllowPoolSuspension());
        dataSource.setMinimumIdle(this.getMinimumIdle());
        dataSource.setConnectionTimeout(this.getConnectionTimeout());
        dataSource.setIdleTimeout(this.getIdleTimeout());
        dataSource.setMaxLifetime(this.getMaxLifetime());
        dataSource.setAutoCommit(this.getAutoCommit());
        dataSource.setConnectionTestQuery(this.getConnectionTestQuery());

        dataSource.addDataSourceProperty("cachePrepStmts", true);
        dataSource.addDataSourceProperty("prepStmtCacheSize", 25000);
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 20048);
        dataSource.addDataSourceProperty("useServerPrepStmts", true);
        dataSource.addDataSourceProperty("initializationFailFast", true);
        dataSource.setPoolName(this.getPoolName());

        return dataSource;
    }
}
