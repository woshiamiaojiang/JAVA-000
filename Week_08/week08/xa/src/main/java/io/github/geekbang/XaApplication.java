package io.github.geekbang;

import lombok.SneakyThrows;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.stream.IntStream;

public class XaApplication {

    public static void main(String[] args) throws Exception {
        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(Objects.requireNonNull(XaApplication.class.getClassLoader().getResource("sharding.yml")).getFile()));
        TransactionTypeHolder.set(TransactionType.XA);
        Connection connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_order (user_id, payment_no, payment_method, delivery_no, delivery_type, status, created_time, updated_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            IntStream.rangeClosed(1, 10).forEach(i -> setValue(preparedStatement));
            IntStream.rangeClosed(1, 10).forEach(i -> setValue(preparedStatement));
            if (Math.random() > 0.5) {
                throw new RuntimeException("ROLLBACK");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
        } finally {
            if (connection != null) {
                connection.close();
            }
            TransactionTypeHolder.clear();
        }
    }

    @SneakyThrows
    private static void setValue(PreparedStatement preparedStatement) {
        preparedStatement.setObject(1, Math.random() > 0.5 ? 0 : 1);
        preparedStatement.setObject(2, 1);
        preparedStatement.setObject(3, 2);
        preparedStatement.setObject(4, 3);
        preparedStatement.setTimestamp(7, new Timestamp(Instant.now().getEpochSecond()));
        preparedStatement.setTimestamp(8, new Timestamp(Instant.now().getEpochSecond()));
        preparedStatement.executeUpdate();
    }
}
