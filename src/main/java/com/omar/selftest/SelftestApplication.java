package com.omar.selftest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.omar.selftest.dao")
public class SelftestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelftestApplication.class, args);
        System.out.println("run success  你好棒");
    }

}
