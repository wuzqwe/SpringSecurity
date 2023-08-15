package com.szq.springsecurity2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//排除Security的配置，让它不启用
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Springsecurity2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springsecurity2Application.class, args);
    }
}
