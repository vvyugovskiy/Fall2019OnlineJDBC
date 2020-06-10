package com.jdbc.practiceMuhtar;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MetaData {

    // JDBC Supports
    // DatabaseMetaData
    // ResultMetadata

    public static void main(String[] args) throws SQLException {

        System.out.println(DBUtility.metadata.getUserName());
        System.out.println(DBUtility.metadata.getDriverVersion());
        System.out.println(DBUtility.metadata.getDatabaseProductName());
        System.out.println(DBUtility.metadata.getDriverName());

        // ResultSet MetaData
        ResultSet result = DBUtility.getResult("Select * From employees");

        ResultSetMetaData resultMeta = result.getMetaData();

        System.out.println("getColumnCount :: " + resultMeta.getColumnCount());
        System.out.println("+++++++++++++++++++++++++++++++++");
        for (int i = 1; i < resultMeta.getColumnCount(); i++) {
            System.out.println(resultMeta.getColumnName(i));
        }

    }


}
