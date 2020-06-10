package com.jdbc.practiceMuhtar;

import java.sql.*;

public class DBUtility {

    private final static String username = ConfigurationReader.getProperty("JDBC_UserName"),
            password = ConfigurationReader.getProperty("JDBC_UserName"),
            url = ConfigurationReader.getProperty("JDBC_URL");

    public static Connection connection;
    public static Statement statement;

    static  DatabaseMetaData metadata;
    static {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            metadata = connection.getMetaData();
        } catch (Exception e) {
        }
    }

    public static ResultSet getResult(String sql) {

        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
        } catch (Exception e) {
        }
        return result;
    }

    public static void tearDown() {
        try {
            connection.close();
        } catch (Exception e) {
        }
    }

    public static void UpdateQuery(String sql) {
        //  INSERT, UPDATE, DELETE, ALTER, TRUNCATE, DROP

        try {
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
        }
    }
}
