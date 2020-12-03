package com.study.shardingspherejdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.study.shardingspherejdbc.mapper")
public class ShardingsphereJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereJdbcApplication.class, args);
    }

}
