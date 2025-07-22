package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.exceptions.EmailAlreadyExistsException;
import com.arthur.schedulingApi.exceptions.PhoneAlreadyExistsException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.UserRoles;
import com.arthur.schedulingApi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.UserMapperToModel.userToModel;
import static com.arthur.schedulingApi.usecases.mapper.UserMapperToResponse.userToResponse;


@Service
@RequiredArgsConstructor
public class CreateUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @CachePut(value = "USER_CACHE" , key = "#result.id()")
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {

        if (userRepository.existsByEmail(userRequestDTO.email())) {
            throw new EmailAlreadyExistsException("O E-mail '" + userRequestDTO.email() + "' já está em uso.");
        }

        if (userRepository.existsByPhoneNumber(userRequestDTO.phoneNumber())) {
            throw new PhoneAlreadyExistsException("O Telefone '" + userRequestDTO.phoneNumber() + "' já está em uso.");
        }

        User userModel = userToModel(userRequestDTO);

        userModel.setEncodePassword(userModel.getPassword() , passwordEncoder);
        userModel.setRole(UserRoles.USER);

        return userToResponse(userRepository.save(userModel));
    }
}