package com.arthur.schedulingApi.controllers.user.request;

import com.arthur.schedulingApi.models.user.UserRoles;

public record UserRequestDTO (Long id,
                              String name,
                              String email,
                              String password,
                              String phoneNumber,
                              String  role){

}


