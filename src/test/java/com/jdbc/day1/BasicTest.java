package com.jdbc.day1;

import java.sql.*;

public class BasicTest {

    public static void main(String[] args) throws SQLException {

        String URL = "jdbc:oracle:thin:@34.229.116.89:1521:xe"; // my server IP
        String username = "hr";
        String password = "hr";

        // to establish connection with DB
        Connection connection = DriverManager.getConnection(URL, username, password);
        // The constant indicating the type for a <code>ResultSet</code> object
        //     * that is scrollable and generally sensitive to changes to the data
        //     * that underlies the <code>ResultSet</code>.
        // ResultSet.CONCUR_READ_ONLY: The constant indicating the concurrency mode for a
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        // resultSet.next() -- returns true
        // and jumps to next row if there is one with data
        while (resultSet.next()) {
            // get data from 2nd column for every row
            System.out.println(resultSet.getString(1) + " | " + resultSet.getString(2) + " | " + resultSet.getString(3));
        }

        resultSet.beforeFirst(); // to come back to the beginning of result set
        // some technical information about database
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        // some technical data about resultSet
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println("JDBC Driver: " + databaseMetaData.getDriverName());
        System.out.println("JDBC Driver Version: " + databaseMetaData.getDriverVersion());
        System.out.println("Database Name: " + databaseMetaData.getDatabaseProductName());
        System.out.println("Database Version: " + databaseMetaData.getDatabaseProductVersion());
        System.out.println("Number of columns: " + resultSetMetaData.getColumnCount());
        System.out.println("Data type of first Column: " + resultSetMetaData.getColumnTypeName(1));

        for (int i = 1; i <= 11; i++) {
            System.out.printf("%-20s",resultSetMetaData.getColumnLabel(i));
            if (i==11){
                System.out.println("\n");
            }
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        // iterate rows
        while (resultSet.next()){
            // iterate columns
            for (int columnIndex = 1; columnIndex <=resultSetMetaData.getColumnCount(); columnIndex++){
                System.out.printf("%-20s",resultSet.getString(columnIndex));
            }
            System.out.println(" ");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
