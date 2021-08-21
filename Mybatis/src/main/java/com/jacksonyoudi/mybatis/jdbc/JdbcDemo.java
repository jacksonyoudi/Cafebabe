package com.jacksonyoudi.mybatis.jdbc;

import java.sql.*;

public class JdbcDemo {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 通过驱动管理类获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "root");

            // 定义sql语句？ 表示占位符
            String sql = "select * from user where username = ?" ;
            // 获取预处理statement
            statement = connection.prepareStatement(sql);
            // 设置参数，第⼀个参数为sql语句中参数的序号(从1开始)，第⼆个参数为设置的参数值
            statement.setString(1, "tom");
            // 向数据库发出sql执⾏查询，查询出结果集
            rs = statement.executeQuery();
            // 遍历查询结果集
            User user = new User();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                // 封 装 User
                user.setId(id);
                user.setUsername(username);
            }

            System.out.println(user);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
