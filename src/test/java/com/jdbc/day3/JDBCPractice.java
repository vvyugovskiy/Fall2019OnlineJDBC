package com.jdbc.day3;

import com.jdbc.utilities.DBUtils;
import org.junit.Test;

import java.sql.*;

public class JDBCPractice {

    static String URL = "jdbc:oracle:thin:@3.86.255.209:1521:xe"; // my server IP
    static String username = "hr";
    static String password = "hr";

    @Test
    public void connectToDB() throws SQLException {

        /**
         * To connect with a database we call DriverManager
         * Provide DB connection URL. username and password
         * By default, JDBC does auto commit
         */
        Connection connection = DriverManager.getConnection(URL, username, password);
//        connection.setAutoCommit(false); to disable auto commit
//        if don't commit, we can call rollback function to revert changes
//        if auto commit is disabled, at the end we have to call connection.commit() method to make changes permanent
//        only DML commands can ge reverted: INSERT, UPDATE, DELETE

        /**
         * statement is used to execute a query
         */
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        /**
         * A table of data representing a database result set,
         * which is usually generated by executing a statement that queries the database
         * It pointer will be before first row, we need to call next() method
         * To switch pointer and move it to the first row
         */
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
        resultSet.next();  //returns boolean, and moves pointer to the next row in result set if it present

        String firstName = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");

        resultSet.beforeFirst(); //to move cursor back before first row
        while (resultSet.next()) {
            String firstName2 = resultSet.getString("first_name");
            String lastName2 = resultSet.getString("last_name");
            System.out.println(firstName2 + " " + lastName2);
        }

        /**
         * either (1) the row count for SQL Data Manipulation Language (DML) statements
         *     or (2) 0 for SQL statements that return nothing
         */
//        statement.executeUpdate("UPDATE employees SET first_name = 'Steven', last_name='King' WHERE employee_id = 101");

        System.out.println("firstName = " + firstName);
        System.out.println("last_name = " + last_name);

        resultSet.close();
        statement.close();
        connection.close();

    }

    /**
     * PreparedStatement helps to easier executed UPDATE statement
     * Good for batch execution
     */
    @Test
    public void preparedStatementTest() throws SQLException {

        Connection connection = DriverManager.getConnection(URL, username, password);

        String query = "SELECT * FROM employees WHERE last_name = ? AND first_name = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, "King");
        preparedStatement.setString(2, "Steven");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));

        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void metaDataTest() throws Exception {
        //try with resources
        try (Connection connection = DriverManager.getConnection(URL, username, password);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees")) {


            DatabaseMetaData databaseMetaData = connection.getMetaData();//database properties, not data itself
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();//result set properties, not a data itself

            while (resultSet.next()) {
                for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
                    System.out.print(resultSet.getObject(columnIndex) + " ");
                }
                System.out.println();
            }
        }
    }

    @Test
    public void dbUtilitiesTest() {
        DBUtils.createConnection(URL, username, password);

        System.out.println(DBUtils.getQueryResultMap("SELECT * FROM employees"));

        DBUtils.destroy();
    }
}