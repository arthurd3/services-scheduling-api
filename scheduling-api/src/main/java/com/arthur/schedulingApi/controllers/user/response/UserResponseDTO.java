package com.arthur.schedulingApi.controllers.user.response;

public record UserResponseDTO (Long id ,
                               String name,
                               String email,
                               String role){
}
