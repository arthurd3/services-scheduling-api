package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.utilities.verify.VerifyEditUser;
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
    private final AuthenticatedUserService  authenticatedUserService;
    private final VerifyEditUser verifyEdit;

    @Transactional
    @CachePut(value = "USER_CACHE" , key = "#result.id()")
    public UserResponseDTO editUser(Long id , UserRequestDTO userRequestDTO) {

        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();

        User originalUser = findUser.findByIdAsModel(id);

        verifyEdit.verifyEdit(authenticatedUser , originalUser);

        var editedUser = copyProperties(originalUser , userRequestDTO);

        return userToResponse(editedUser);
    }
}