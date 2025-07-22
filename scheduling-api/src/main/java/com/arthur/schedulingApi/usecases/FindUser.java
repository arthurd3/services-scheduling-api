package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import static com.arthur.schedulingApi.usecases.mapper.UserMapperToResponse.userToResponse;

@Service
@RequiredArgsConstructor
public class FindUser {

    private final UserRepository userRepository;

    @Cacheable(value = "USER_CACHE", key = "#userId")
    public UserResponseDTO findById(Long userId) {

         var returnedUser = userRepository.findById(userId)
                 .orElseThrow(() -> new UserNotFoundException("Usuario com id "+ userId +" nao encontrado!"));
         return userToResponse(returnedUser);
    }

    public User findByIdAsModel(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario com id "+ userId +" nao encontrado!"));
    }
}