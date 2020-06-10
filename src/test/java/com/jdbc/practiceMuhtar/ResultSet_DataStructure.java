package com.jdbc.practiceMuhtar;

import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSet_DataStructure {

    public static void main(String[] args) throws SQLException {

        ResultSet result = DBUtility.getResult("Select * From employees");

        List<String> EmployeesNames = new ArrayList<>();

        while (result.next()) {
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");

            String fullName = firstName + " " + lastName;
            EmployeesNames.add(fullName);
                    }
        System.out.println(EmployeesNames.contains("Steven King"));
        Assert.assertTrue(EmployeesNames.contains("Steven King"));
    }
}
