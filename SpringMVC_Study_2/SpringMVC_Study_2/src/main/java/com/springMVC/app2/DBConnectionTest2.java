package com.springMVC.app2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionTest2 {
    public static void main(String[] args) throws SQLException {
        // 스키마의 이름(springbasic)이 다른 경우 알맞게 변경
        /* //아래의 28~29번 2줄은 빈으로 등록해둔 것을 가져와서 사용하는것, 15~26은 빈으로 등록하지 않고 직접 객체를 생성한 것
        String DB_URL = "jdbc:mysql://localhost:3306/springmvc_study?useUnicode=true&characterEncoding=utf8";

        String DB_USER = "Beudicri";
        String DB_PASSWORD = "root";
        String DB_DRIVER = "com.mysql.jdbc.Driver";

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DB_DRIVER);
        ds.setUrl(DB_URL);
        ds.setUsername(DB_USER);
        ds.setPassword(DB_PASSWORD);
         */


        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        //assertTrue(conn!=null);
    }
}
