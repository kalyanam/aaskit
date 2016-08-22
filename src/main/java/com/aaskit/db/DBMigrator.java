package com.aaskit.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

/**
 * Created by mkalyan on 8/22/16.
 */
public class DBMigrator {
    public void migrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(createDataSource("org.postgresql.Driver",System.getenv("JDBC_DATABASE_URL")));
        flyway.migrate();
    }

    private DataSource createDataSource(String driverName, String dbUrl) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(dbUrl);

        return dataSource;
    }
}
