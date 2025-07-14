package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUser {

    private final UserRepository userRepository;

    public FindUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserResponseDTO> findUserAsDto(Long userId) {
        return userRepository.findById(userId)
                .map(UserMapperToResponse::userToResponse);
    }

    public Optional<User> findUserEntity(Long userId) {
        return userRepository.findById(userId);
    }
}
