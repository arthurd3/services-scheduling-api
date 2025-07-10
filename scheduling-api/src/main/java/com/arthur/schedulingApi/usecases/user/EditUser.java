package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.usecases.user.mapper.UserCopyProperties.copyProperties;
import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse.userToResponse;


@Service
public class EditUser {

    private final UserRepository userRepository;

    public EditUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserResponseDTO> editUser(Long id , UserRequestDTO userRequestDTO) {
        Optional<User> originalUser = userRepository.findById(id);

        if(originalUser.isEmpty())
            return Optional.empty();

        var editedUser = copyProperties(originalUser.get() , userRequestDTO);

        userRepository.save(editedUser);

        return Optional.of(userToResponse(editedUser));
    }

}
