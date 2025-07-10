package com.arthur.schedulingApi.config;

import com.arthur.schedulingApi.usecases.user.mapper.UserMapperToModel;
import com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserMapperToResponse userMapperToResponse() {
        return new UserMapperToResponse();
    }

    @Bean
    public UserMapperToModel userMapperToModel() {
        return new UserMapperToModel();
    }

}
