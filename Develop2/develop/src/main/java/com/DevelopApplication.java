package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class DevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevelopApplication.class, args);
    }

}
