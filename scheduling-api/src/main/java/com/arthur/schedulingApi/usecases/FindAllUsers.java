package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.repositories.UserRepository;

import com.arthur.schedulingApi.usecases.mapper.UserMapperToResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FindAllUsers {
    private final UserRepository userRepository;


    public FindAllUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponseDTO> findAllUsers(PageRequest pageRequest){

        Page<User> userPage = userRepository.findAll(pageRequest);
        return userPage.map(UserMapperToResponse::userToResponse);

    }
}
