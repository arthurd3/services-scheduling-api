package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse.userToResponse;

@Service
public class FindUser {

    private final UserRepository userRepository;

    public FindUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserResponseDTO> findUser(Long userId) {

        if (userRepository.existsById(userId))
            return Optional.of(userToResponse(userRepository.findById(userId).get()));

        return Optional.empty();
    }
}
