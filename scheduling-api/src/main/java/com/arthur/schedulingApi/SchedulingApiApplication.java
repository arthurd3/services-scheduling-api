package com.arthur.schedulingApi;

import com.arthur.schedulingApi.usecases.InsertAdminUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@RequiredArgsConstructor
@EnableScheduling
public class SchedulingApiApplication {

    private final InsertAdminUser insert;

    public static void main(String[] args) {
        SpringApplication.run(SchedulingApiApplication.class, args);
    }

    @Bean
    InitializingBean initializeData() {
        return insert::insertAdminUser;
    }
}
