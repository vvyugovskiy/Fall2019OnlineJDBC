package com.jdbc.practiceMuhtar;

import java.sql.*;

public class Statement_ResultSet {

    public static void main(String[] args) throws SQLException {

        String userName = "hr"; // 1)
        String passWord = "hr"; // 2)

        // url syntax:  jdbc:DataBaseType:thin@Host:port:SID
        String url = "jdbc:oracle:thin:@3.91.62.1:1521:xe"; // 3)

        // Connection :: import java.sql.Connection;
        // DriverManager :: import java.sql.DriverManager;
        Connection connection  = DriverManager.getConnection(url, userName,passWord);
        System.out.println("1. Connect completed");

        // after successfully creating connection  create Statement
        // Use createStatement() method to create statement from our Connection
        Statement statement = connection.createStatement();
        System.out.println("2. Statement is created");

        // after we have statement we can run the QUERY and get the result to ResultSet format
        // import java.sql.ResultSet
        // use executeQuery()
        // ResultSet is Data Structure that stores returned QUERY
        ResultSet resultSet = statement.executeQuery("Select * From employees");
        System.out.println("3. ResultSet is completed");
        // use next() method to iterate each row
        // ResultSet Methods:
        // next()   getString(ColumnName)   getString(Index)    getInt(ColumnName)   getInt(Index)
        // getDouble(ColumnName)            getDouble(Index)    getDate(ColumnName)  getDate(Index)

        String sql = "";
        statement.executeUpdate("update employees set first_name = 'Vlad' where employee_id = 100");
        System.out.println("Update executed!");
//
//        resultSet.close();
//        statement.close();
//        connection.close();

    }
}
