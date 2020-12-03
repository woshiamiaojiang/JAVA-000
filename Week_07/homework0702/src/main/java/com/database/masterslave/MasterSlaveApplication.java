package com.database.masterslave;

import com.database.masterslave.util.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@MapperScan(basePackages = "com.database.masterslave.autocreate.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
public class MasterSlaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterSlaveApplication.class, args);
    }

}
