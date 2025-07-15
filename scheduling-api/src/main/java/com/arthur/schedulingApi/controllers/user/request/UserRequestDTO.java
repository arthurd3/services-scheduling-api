package com.arthur.schedulingApi.controllers.user.request;

public record UserRequestDTO (String name,
                              String email,
                              String password,
                              String phoneNumber){
}


