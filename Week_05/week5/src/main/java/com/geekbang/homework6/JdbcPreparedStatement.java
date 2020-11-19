package com.geekbang.homework6;

import java.sql.*;

public class JdbcPreparedStatement {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "UPDATE users SET age = age + 1 WHERE id = 1";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                con.setAutoCommit(false);
                preparedStatement.setInt(1, 100);
                preparedStatement.executeUpdate();
                preparedStatement.setInt(1, 103);
                preparedStatement.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                try {
                    e.printStackTrace();
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
