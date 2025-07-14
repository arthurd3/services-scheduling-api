package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.utilities.copyproperties.UserCopyProperties.copyProperties;
import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse.userToResponse;


@Service
public class EditUser {

    private final FindUser findUser;
    public EditUser(FindUser findUser) {
        this.findUser = findUser;
    }

    @Transactional
    public Optional<UserResponseDTO> editUser(Long id , UserRequestDTO userRequestDTO) {

        User originalUser = findUser.findUserEntity(id);

        var editedUser = copyProperties(originalUser , userRequestDTO);

        return Optional.of(userToResponse(editedUser));
    }

}
