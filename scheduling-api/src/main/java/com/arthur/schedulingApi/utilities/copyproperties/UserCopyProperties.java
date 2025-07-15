package com.arthur.schedulingApi.utilities.copyproperties;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.models.user.User;

import static com.arthur.schedulingApi.utilities.copyproperties.GetUpdateValue.getUpdatedValue;

public class UserCopyProperties {

    public static User copyProperties(User originalUser, UserRequestDTO userRequestDTO) {

        originalUser.setName(getUpdatedValue(userRequestDTO.name(), originalUser.getName()));
        originalUser.setEmail(getUpdatedValue(userRequestDTO.email(), originalUser.getEmail()));
        originalUser.setPhoneNumber(getUpdatedValue(userRequestDTO.phoneNumber(), originalUser.getPhoneNumber()));

        return originalUser;
    }


}