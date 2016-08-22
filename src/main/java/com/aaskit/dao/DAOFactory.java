package com.aaskit.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by mkalyan on 8/22/16.
 */
public class DAOFactory {
    private JdbcTemplate template;

    private DAOFactory(JdbcTemplate template) {
        this.template = template;
    }

    public LoginDetailsDAO getUserDetailsDAO() {
        return new LoginDetailsDAO(this.template);
    }

    public EmployerDAO getEmployerDAO() {
        return new EmployerDAO(this.template);
    }

    public PostingDAO getPostingDAO() {
        return new PostingDAO(this.template);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String driverName;
        private String dbUrl;

        public Builder withDriverName(String driverName) {
            this.driverName = driverName;
            return this;
        }

        public Builder withDbUrl(String dbUrl) {
            this.dbUrl = dbUrl;
            return this;
        }

        private DataSource createDataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(this.driverName);
            dataSource.setUrl(this.dbUrl);

            return dataSource;
        }

        public DAOFactory build() {
            JdbcTemplate template = new JdbcTemplate(createDataSource());
            return new DAOFactory(template);
        }
    }
}
