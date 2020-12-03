package jdbc;


import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
public class JDBCTestThread {
    public static void main(String[] args) throws SQLException {
        ExecutorService executor = Executors.newFixedThreadPool(16);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                saveData();
            });
            log.info("call save method" + i);
        }
        long endTime = System.currentTimeMillis();
        log.info("main thread execute time" + (endTime - startTime) / 1000 + "s");
        executor.shutdown();
    }

    private static void saveData() {
        try {
            long startTime = System.currentTimeMillis();
            //通过工具类获取数据库连接对象
            Connection con = JDBCUtils.getConnection();
            String insertSQL = "insert  into t_user_info (username,password,phone,email) values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(insertSQL);
            for (int i = 0; i < 10000; i++) {
                pst.setString(1, "用户名");
                pst.setString(2, "123456");
                pst.setString(3, "18019409909");
                pst.setString(4, "18019409909@163.com");
                pst.addBatch();
            }
            pst.executeBatch();

            JDBCUtils.close(con, pst, null);
            long endTime = System.currentTimeMillis();
            double executeTime = (endTime - startTime) / 1000.00;
            log.info("child thread execution time:" + executeTime + "s");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

