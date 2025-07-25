package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.repositories.UserRepository;

import com.arthur.schedulingApi.usecases.mapper.UserMapperToResponse;
import jakarta.persistence.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAllUsers {

    private final UserRepository userRepository;

    public Page<UserResponseDTO> findAllUsers(PageRequest pageRequest){

        Page<User> userPage = userRepository.findAll(pageRequest);
        return userPage.map(UserMapperToResponse::userToResponse);
    }
}