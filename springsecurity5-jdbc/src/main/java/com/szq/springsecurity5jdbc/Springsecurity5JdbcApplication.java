package com.szq.springsecurity5jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class Springsecurity5JdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springsecurity5JdbcApplication.class, args);
    }

}
