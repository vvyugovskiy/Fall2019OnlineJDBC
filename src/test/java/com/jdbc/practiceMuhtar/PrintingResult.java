package com.jdbc.practiceMuhtar;

import java.sql.*;

public class PrintingResult {

    public static void main(String[] args) throws SQLException {

        String userName = "hr"; // 1)
        String passWord = "hr"; // 2)

        String url = "jdbc:oracle:thin:@3.91.62.1:1521:xe"; // 3)

        Connection connection  = DriverManager.getConnection(url, userName,passWord);
        System.out.println("1. Connect completed");

        Statement statement = connection.createStatement();
        System.out.println("2. Statement is created");

        ResultSet result = statement.executeQuery("Select * From employees");
//        ResultSet result1 = statement.executeQuery("Select first_name || ' ' || last_name  As full_name From employees");

        System.out.println("3. ResultSet is completed");

        while (result.next()){
            String firstname = result.getString(2);
            String lastname = result.getString(3);
            int salary = result.getInt("salary");
            System.out.println(firstname +" | "+ lastname+" | $"+salary);
        }
    }

}
