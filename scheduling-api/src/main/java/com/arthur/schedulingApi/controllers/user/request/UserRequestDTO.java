package com.arthur.schedulingApi.controllers.user.request;

public record UserRequestDTO (Long id,
                              String name,
                              String email,
                              String password,
                              String phoneNumber,
                              String role){

}


