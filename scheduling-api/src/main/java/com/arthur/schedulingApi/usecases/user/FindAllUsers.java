package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.repositories.UserRepository;
import com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class FindAllUsers {
    private final UserRepository userRepository;
    private final UserMapperToResponse userMapperToResponse;

    public FindAllUsers(UserRepository userRepository, UserMapperToResponse userMapperToResponse) {
        this.userRepository = userRepository;
        this.userMapperToResponse = userMapperToResponse;
    }

    public Page<UserResponseDTO> findAllUsers(PageRequest pageRequest){
        var pageToResponse = userRepository.findAll(pageRequest);
        return pageToResponse.map(userMapperToResponse::UserToResponse);
    }
}
