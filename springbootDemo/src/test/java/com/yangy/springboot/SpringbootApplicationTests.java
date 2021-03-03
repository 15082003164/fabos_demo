package com.yangy.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
class SpringbootApplicationTests {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;


    /**
     * 测试数据库是否能连接成功
     *
     * @throws SQLException
     */
    @Test
    void contextLoads() throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            System.out.println("加载成功");
        } catch (Exception ex) {
            System.out.println("加载失败");
            // handle the error
        }

        try {
            conn = DriverManager.getConnection(
                    url,
                    username,
                    password);

            System.out.println("连接成功");
            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("连接失败");
        }
    }
}