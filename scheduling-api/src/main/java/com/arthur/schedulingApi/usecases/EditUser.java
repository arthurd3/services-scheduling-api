package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.models.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.utilities.copyproperties.UserCopyProperties.copyProperties;
import static com.arthur.schedulingApi.usecases.mapper.UserMapperToResponse.userToResponse;


@Service
@RequiredArgsConstructor
public class EditUser {

    private final FindUser findUser;

    @Transactional
    @CachePut(value = "USER_CACHE" , key = "#result.id()")
    public UserResponseDTO editUser(Long id , UserRequestDTO userRequestDTO) {

        User originalUser = findUser.findByIdAsModel(id);

        var editedUser = copyProperties(originalUser , userRequestDTO);

        return userToResponse(editedUser);
    }
}