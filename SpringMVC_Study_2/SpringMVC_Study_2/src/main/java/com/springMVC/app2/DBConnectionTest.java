package com.springMVC.app2;

import java.sql.*;

public class DBConnectionTest {
    public static void main(String[] args) throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/springmvc_study?useUnicode=true&characterEncoding=utf8";

        String DB_USER = "Beudicri";
        String DB_PASSWORD = "root";

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement stmt = conn.createStatement();

        String query = "SELECT NOW()";
        ResultSet resultSet = stmt.executeQuery(query);

        while (resultSet.next()) {
            String curDate = resultSet.getString(1);
            System.out.println(curDate);
        }
    }
}
