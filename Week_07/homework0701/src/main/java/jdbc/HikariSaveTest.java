package jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
public class HikariSaveTest implements CommandLineRunner {
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(HikariSaveTest.class);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("begin to insert data......");
        long startTime = System.currentTimeMillis();
        showConnection();
        saveData();
        long endTime = System.currentTimeMillis();
        System.out.println("execution time" + (startTime - endTime) / 1000 + "s");

    }

    private void saveData() {
        for (int i = 0; i < 1000000; i++) {
            jdbcTemplate.execute("insert  into t_user_info (username,password,phone,email) values('用户名','123456','18019409909','18019409909@163.com')");
        }

    }

    private void showConnection() throws SQLException {
        log.info(dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.info(conn.toString());

    }


}
