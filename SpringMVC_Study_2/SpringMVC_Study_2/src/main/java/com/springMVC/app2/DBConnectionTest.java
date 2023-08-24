package com.springMVC.app2;

import java.sql.*;

public class DBConnectionTest {
    public static void main(String[] args) throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/springmvc_study?useUnicode=true&characterEncoding=uft8";

        String DB_USER = "Beudicri";
        String DB_Password = "root";

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_Password);
        Statement stmt = conn.createStatement();

        String query = "SELECT NOW()";
        ResultSet resultSet = stmt.executeQuery(query);

        while (resultSet.next()) {
            String curDate = resultSet.getString(1);
            System.out.println(curDate);
        }
    }
}
