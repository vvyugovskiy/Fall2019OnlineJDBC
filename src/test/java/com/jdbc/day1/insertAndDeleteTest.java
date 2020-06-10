package com.jdbc.day1;

import java.sql.*;

public class insertAndDeleteTest {

    static String URL = "jdbc:oracle:thin:@3.86.255.209:1521:xe"; // my server IP
    static String username = "hr";
    static String password = "hr";

    public static void main(String[] args) throws SQLException {
        String INSERT_QUERY = "INSERT INTO employees VALUES (115, 'Hasan','Mammadov','hasan@cybertek.com','777-777-7777',SYSDATE,'SDET', 14999,0, NULL, NULL)";
        String DELETE_QUERY = "DELETE FROM employees WHERE employee_id = 223";

        try (Connection connection = DriverManager.getConnection(URL, username, password);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery(INSERT_QUERY);
        ) {
        }
//        resultSet.close();
//        statement.close();
//        connection.close();


    }
}
