package com.arthur.schedulingApi.usecases.copyproperties;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.models.user.UserRoles;

import static com.arthur.schedulingApi.usecases.copyproperties.GetUpdateValue.getUpdatedValue;

public class UserCopyProperties {

    public static User copyProperties(User originalUser, UserRequestDTO userRequestDTO) {

        originalUser.setName(getUpdatedValue(userRequestDTO.name(), originalUser.getUsername()));
        originalUser.setEmail(getUpdatedValue(userRequestDTO.email(), originalUser.getEmail()));
        originalUser.setPhoneNumber(getUpdatedValue(userRequestDTO.phoneNumber(), originalUser.getPhoneNumber()));


        if (userRequestDTO.role() != null) {
            try {
                UserRoles updatedRole = UserRoles.valueOf(userRequestDTO.role().toUpperCase());
                originalUser.setRole(updatedRole);
            } catch (IllegalArgumentException e) {
                throw e;
            }
        }

        return originalUser;

    }


}