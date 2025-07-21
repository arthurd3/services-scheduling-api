package com.arthur.schedulingApi.usecases.mapper;

import com.arthur.schedulingApi.controllers.response.UserResumeDTO;
import com.arthur.schedulingApi.models.User;

public class UserMapperToResume {

    public static UserResumeDTO userToResume(User userModel) {

        return new UserResumeDTO(
                userModel.getId() ,
                userModel.getName() ,
                userModel.getEmail() ,
                userModel.getPhoneNumber()
        );
    }
}
