package com.jdbc.practiceMuhtar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultSet_Map {

    public static void main(String[] args) throws SQLException {

        ResultSet result = DBUtility.getResult("Select * From employees");

        Map<String,Integer> map = new LinkedHashMap<>();
        while (result.next()) {
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            String fullName = firstName + " " + lastName;
            int salary = result.getInt("salary");

            map.put(fullName,salary);
                    }
        System.out.println(map);
        System.out.println(map.get("Steven King")==24000);

        String sql = "Update employees Set first_name = 'Vladislav' Where employee_id = 100";
    }
}
