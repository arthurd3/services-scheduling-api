package com.arthur.schedulingApi.usecases.copyproperties;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.models.user.UserRoles;

import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedValue;

public class UserCopyProperties {

    public static User copyProperties(User originalUser, UserRequestDTO userRequestDTO) {
        UserRoles userRole;
        if (userRequestDTO.role() == null) userRole = originalUser.getRole();

        else userRole = UserRoles.valueOf(userRequestDTO.role().toUpperCase());

        return new User(
                originalUser.getId(),
                getUpdatedValue(userRequestDTO.name(), originalUser.getUsername()),
                getUpdatedValue(userRequestDTO.email(), originalUser.getEmail()),
                getUpdatedValue(userRequestDTO.password(), originalUser.getPassword()),
                getUpdatedValue(userRequestDTO.phoneNumber(), originalUser.getPhoneNumber()),
                userRole
        );
    }


}