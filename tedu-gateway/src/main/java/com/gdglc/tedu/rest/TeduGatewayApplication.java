package com.gdglc.tedu.rest;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableDubboConfiguration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TeduGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeduGatewayApplication.class, args);
    }

}
