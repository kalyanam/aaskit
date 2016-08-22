package com.aaskit.db;

import com.heroku.sdk.jdbc.DatabaseUrl;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkalyan on 8/21/16.
 */
public class DBHelper {
    private boolean executeDDL(String ddl) {
        Connection connection = null;
        try {
            String dbUrl = System.getenv("JDBC_DATABASE_URL");

            System.out.println("JDBC URL is: "+dbUrl);
            connection = DriverManager.getConnection(dbUrl); //DatabaseUrl.extract().getConnection();
            System.out.println("Driver name is: "+DriverManager.getDriver(dbUrl).getClass().getName());

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(ddl);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
            }
        }

        return true;
    }

    public boolean executeUpdate(String sql) {
        Connection connection = null;
        Map<String, Object> attributes = new HashMap<>();
        try {
            String dbUrl = System.getenv("JDBC_DATABASE_URL");

            System.out.println("JDBC URL is: "+dbUrl);
            connection = DriverManager.getConnection(dbUrl); //DatabaseUrl.extract().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
            }
        }

        return true;
    }

    private Map<String, Object> executeQuery(String sql) {
        Connection connection = null;
        Map<String, Object> attributes = new HashMap<>();
        try {
            connection = DatabaseUrl.extract().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
                output.add("Read from DB: " + rs.getTimestamp("tick"));
            }

            attributes.put("results", output);
        } catch (Exception e) {
            attributes.put("message", "There was an error: " + e);
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
            }
        }

        return attributes;
    }
}
