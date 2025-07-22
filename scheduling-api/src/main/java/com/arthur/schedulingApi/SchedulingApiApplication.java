package com.arthur.schedulingApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SchedulingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulingApiApplication.class, args);
    }

}
