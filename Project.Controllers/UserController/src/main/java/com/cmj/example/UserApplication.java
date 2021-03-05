package com.cmj.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/3/5
 */
@SpringBootApplication
@MapperScan(basePackages = "com.cmj.example.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
