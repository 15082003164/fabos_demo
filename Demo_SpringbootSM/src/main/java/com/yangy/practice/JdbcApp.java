package com.yangy.practice;

import java.sql.*;

public class JdbcApp {

    /**
     * - 加载JDBC驱动程序
     * - 创建数据库的连接
     * - 创建preparedStatement
     * - 执行SQL语句
     * - 处理结果集
     * - 关闭JDBC对象资源
     */

    public static void main(String[] args) throws Exception{

        String url = "jdbc:mysql://127.0.0.1:3306/user?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8";

        String username = "root";

        String password = "12345678";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url,username,password);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from user");

        while (resultSet.next()){
            System.out.println("num:" + resultSet.getString("name") + " classname:" + resultSet.getString("classname"));
        }

        statement.close();

    }
}
