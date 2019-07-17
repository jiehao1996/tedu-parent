package com.gdglc.tedu.rest;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.gdglc.tedu.rest.modular.UserImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDubboConfiguration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TeduUserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TeduUserApplication.class, args);

        UserImpl user = (UserImpl) run.getBean("userImpl");
        user.sendMessage("这里是tedu-user.RUN");
    }

}
