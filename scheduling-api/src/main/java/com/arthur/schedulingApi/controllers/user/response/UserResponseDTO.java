package com.arthur.schedulingApi.controllers.user.response;

import com.arthur.schedulingApi.models.user.UserRoles;

public record UserResponseDTO (Long id ,
                               String name,
                               String email,
                               String phoneNumber,
                               String role){
}
