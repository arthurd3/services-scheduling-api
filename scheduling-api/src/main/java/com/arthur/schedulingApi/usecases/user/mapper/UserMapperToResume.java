package com.arthur.schedulingApi.usecases.user.mapper;

import com.arthur.schedulingApi.controllers.user.response.UserResumeDTO;
import com.arthur.schedulingApi.models.user.User;

public class UserMapperToResume {

    public static UserResumeDTO userToResume(User userModel) {

        return new UserResumeDTO(
                userModel.getId() ,
                userModel.getUsername() ,
                userModel.getEmail() ,
                userModel.getPhoneNumber()
        );

    }
}
