package com.geekbang.homework6;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class JdbcHikariCp {

    public static void main(String[] args) {
        String configFile = JdbcHikariCp.class.getClassLoader().getResource("db.properties").getPath();
        HikariDataSource ds = new HikariDataSource(new HikariConfig(configFile));

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            pst = con.prepareStatement("select * from users");
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.format("%d %s %d %n", rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JdbcHikariCp.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
                ds.close();
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(JdbcHikariCp.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}
