package com.arthur.schedulingApi.utilities.copyproperties;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.models.user.UserRoles;

import static com.arthur.schedulingApi.utilities.copyproperties.GetUpdateValue.getUpdatedValue;

public class UserCopyProperties {

    public static User copyProperties(User originalUser, UserRequestDTO userRequestDTO) {

        originalUser.setName(getUpdatedValue(userRequestDTO.name(), originalUser.getUsername()));
        originalUser.setEmail(getUpdatedValue(userRequestDTO.email(), originalUser.getEmail()));
        originalUser.setPhoneNumber(getUpdatedValue(userRequestDTO.phoneNumber(), originalUser.getPhoneNumber()));


        if (userRequestDTO.role() != null) {
            UserRoles updatedRole = UserRoles.valueOf(userRequestDTO.role().toUpperCase());
            originalUser.setRole(updatedRole);
        }

        return originalUser;

    }


}