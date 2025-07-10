package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse.userToResponse;

@Service
public class DeleteUser {


    private final UserRepository userRepository;

    public DeleteUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean deleteUser(Long userId) {

        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return true;
        }

        return false;

    }

}
