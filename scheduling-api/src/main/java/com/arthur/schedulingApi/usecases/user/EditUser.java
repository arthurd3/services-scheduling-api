package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.models.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.utilities.copyproperties.UserCopyProperties.copyProperties;
import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse.userToResponse;


@Service
public class EditUser {

    private final FindUser findUser;

    public EditUser(FindUser findUser) {
        this.findUser = findUser;
    }

    @Transactional
    public UserResponseDTO editUser(Long id , UserRequestDTO userRequestDTO) {

        User originalUser = findUser.findUserEntity(id);

        var editedUser = copyProperties(originalUser , userRequestDTO);

        return userToResponse(editedUser);
    }

}
