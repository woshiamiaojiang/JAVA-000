package com.database.masterslave.insert100;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.concurrent.TimeUnit;

public class NewUserLogic {

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true&characterEncoding=utf-8&serverTimezone=UTC";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";


    public static void main(String[] args) {

        addExecuteBatch(1); //提交一次
       // addExecuteBatch(10);       //提交10次
       // addExecuteBatch(100);      //提交100次
       // addExecuteBatch(1000);     //提交1000次
       // addExecuteBatch(10000);     //提交10000次
       // addExecuteBatch(100000);     //提交100000次
       // addExecuteBatch(1000000);     //每次提交
    }

    /**
     * 批处理方式
     */
    public static void addExecuteBatch(int number) {

        Connection conn = null;
        PreparedStatement ps = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
            String sql = "INSERT INTO user(id,username,password,mobile,email,real_name,s_did,did_type,is_business,address,create_time,update_time) VALUES(?,?,?,?,?,?,?,?,?,?,now(),now())";
            ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            int sum = 1000000;
            int size = sum/number;

            for (int i=0;i<sum;i++) {
                ps.setString(1, i+"");
                ps.setString(2, "test"+i);
                ps.setString(3, "123456");
                ps.setString(4, "18019409909");
                ps.setString(5, "18019409909@163.com");
                ps.setString(6, "用户名");
                ps.setString(7, "321011199304080000");
                ps.setString(8, "518054");
                ps.setInt(9, 0);
                ps.setString(10, "广东省深圳市南山区");
                ps.addBatch();
                if(i!=0 && i%size == 0){
                    //提交事务
                    conn.commit();
                }
            }
            long startTime = System.nanoTime();//纳秒 比毫秒的精度高

            ps.executeBatch();
            //提交事务
            conn.commit();

            long endTime = System.nanoTime(); //纳秒, 结束时间

            long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
            System.out.println("批处理方式" + number + "次提交共耗时:" + millis + "毫秒");

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
