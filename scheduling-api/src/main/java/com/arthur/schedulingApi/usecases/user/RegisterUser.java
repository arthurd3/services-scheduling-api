package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToModel.userToModel;
import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse.userToResponse;


@Service
public class RegisterUser {

    private final UserRepository userRepository;

    public RegisterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        var userModel = userToModel(userRequestDTO);
        return userToResponse(userRepository.save(userModel));
    }


}
