package com.cmj.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mengjie_chen
 * @description date 2020/11/10
 */
@SpringBootApplication
@MapperScan(basePackages = "com.cmj.example.mapper")
public class ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }

}
