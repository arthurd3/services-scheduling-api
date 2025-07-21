package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import org.springframework.stereotype.Service;


import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse.userToResponse;

@Service
public class FindUser {

    private final UserRepository userRepository;

    public FindUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO findUserAsDto(Long userId) {
         var returnedUser = userRepository.findById(userId)
                 .orElseThrow(() -> new UserNotFoundException("Usuario com id "+ userId +" nao encontrado!"));
         return userToResponse(returnedUser);
    }

    public User findUserEntity(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario com id "+ userId +" nao encontrado!"));
    }

}
