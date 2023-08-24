package com.springMVC.app2;

import com.mysql.cj.jdbc.ConnectionImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.ResultSet;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
//추가해둔 빈을 이용해서 테스트한다.
// root-context 대신 새로운 파일로 만들어도 상관없음
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test {
    @Autowired
    DataSource ds;

    @Test
    public void selectUserTest() throws SQLException {
        User user = new User("Test2", "1234",
                "Test", "Test@test", new Date(), "facebook", new Date());
        insertUser(user);

        User res = selectUser("Test2");

        assertTrue(res.getId().equals("Test2"));
    }

    public User selectUser(String id) throws SQLException {
        Connection conn = ds.getConnection();

        String sql = "SELECT * FROM user_info WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        ResultSet resultSet = pstmt.executeQuery(); //select일때 executeQuery()사용

        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getString(1));
            user.setPwd(resultSet.getString(2));
            user.setName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setBirth(resultSet.getDate(5));
            user.setSns(resultSet.getString(6));
            user.setReg_date(resultSet.getDate(7));

            return user;
        }

        return null;
    }

    /**
     * 사용자 정보를 user_info테이블에 저장하는 메서드
     * @return
     */
    @Test
    public void insertUserTest() throws SQLException {
        User user = new User("Test2", "1234",
                "Test", "Test@test", new Date(), "facebook", new Date());
        deleteAll();

        int rowCnt = insertUser(user);
        assertTrue(rowCnt==1);
    }
    public int insertUser(User user) throws SQLException {
        Connection conn = ds.getConnection();

        String sql = "insert into user_info " +
                "values (?, ?, ?, ?, ?, ?, now());";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        int rowCnt = pstmt.executeUpdate();

        return rowCnt;
    }


    private void deleteAll() throws SQLException {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    @Test
    public void deleteUserTest() throws SQLException {
        deleteAll();
        int rowCnt = deleteUser("Test2");

        assertTrue(rowCnt == 0);
    }

    private int deleteUser(String id) throws SQLException {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info where id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        return pstmt.executeUpdate(); //insert, update, delete일 때 executeUpdate()사용
    }

    @Test
    public void updateUserTest() throws SQLException {
        User user = new User("Test", "1234",
                "Test", "Test@test", new Date(), "facebook", new Date());

        int rowCnt = updateUser(user);
        assertTrue(rowCnt == 0);
    }

    private int updateUser(User user) throws SQLException {
        Connection conn = ds.getConnection();

        String sql = "UPDATE user_info SET name=? WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "TestTest");
        pstmt.setString(2, "Test");

        return pstmt.executeUpdate();

    }

    @Test
    public void springJdbcConnectionTest() throws SQLException {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn != null); //괄호 안의 조건식이 true면 성공, 아니면 실패
    }
}
