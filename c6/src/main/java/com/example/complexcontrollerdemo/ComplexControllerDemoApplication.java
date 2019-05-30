package com.example.complexcontrollerdemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableCaching
@SpringBootApplication
public class ComplexControllerDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run (ComplexControllerDemoApplication.class, args);
    }

}
