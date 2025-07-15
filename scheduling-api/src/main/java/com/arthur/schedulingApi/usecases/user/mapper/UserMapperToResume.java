package com.arthur.schedulingApi.usecases.user.mapper;

import com.arthur.schedulingApi.controllers.user.resume.UserResumeDTO;
import com.arthur.schedulingApi.models.user.User;

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
