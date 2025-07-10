package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.repositories.UserRepository;

import com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUsers {
    private final UserRepository userRepository;


    public FindAllUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> findAllUsers(PageRequest pageRequest){
        var pageToResponse = userRepository.findAll(pageRequest);
        return pageToResponse.map(UserMapperToResponse::userToResponse).stream().toList();
    }
}
