package jdbc;


import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Slf4j
public class JDBCSaveTest {
    public static void main(String[] args) throws SQLException {
        long startTime = System.currentTimeMillis();
        //通过工具类获取数据库连接对象
        Connection con = JDBCUtils.getConnection();
        String insertSQL = "insert  into t_user_info (username,password,phone,email) values(?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(insertSQL);
        for (int i = 1; i < 1000000; i++) {
            pst.setString(1, "用户名");
            pst.setString(2, "123456");
            pst.setString(3, "18019409909");
            pst.setString(4, "18019409909@163.com");
            pst.addBatch();
        }
        pst.executeBatch();

        JDBCUtils.close(con, pst, null);
        long endTime = System.currentTimeMillis();
        log.info("execute time" + (startTime - endTime) / 1000 + "s");

    }
}
