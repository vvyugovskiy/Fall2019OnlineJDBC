package com.jdbc.practiceMuhtar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleConnection {

    public static void main(String[] args) throws SQLException {


        String userName = "hr"; // 1)
        String passWord = "hr"; // 2)

        // url syntax:  jdbc:DataBaseType:thin@Host:port:SID
        String url = "jdbc:oracle:thin:@3.91.62.1:1521:xe"; // 3)

        // Connection :: import java.sql.Connection;
        // DriverManager :: import java.sql.DriverManager;
        Connection connection  = DriverManager.getConnection(url, userName,passWord);
        System.out.println("Connect Successfully");

        // after successfully creating connection  create Statement
        // Use createStatement() method to create statement from our Connection
        Statement statement = connection.createStatement();





    }
}
