package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.exceptions.EmailAlreadyExistsException;
import com.arthur.schedulingApi.exceptions.PhoneAlreadyExistsException;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToModel.userToModel;
import static com.arthur.schedulingApi.usecases.user.mapper.UserMapperToResponse.userToResponse;


@Service
public class RegisterUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {

        if (userRepository.existsByEmail(userRequestDTO.email())) {
            throw new EmailAlreadyExistsException("O e-mail '" + userRequestDTO.email() + "' já está em uso.");
        }

        if (userRepository.existsByPhoneNumber(userRequestDTO.phoneNumber())) {
            throw new PhoneAlreadyExistsException("O telefone '" + userRequestDTO.phoneNumber() + "' já está em uso.");
        }

        User userModel = userToModel(userRequestDTO);

        userModel.setEncodePassword(userModel.getPassword() , passwordEncoder);

        return userToResponse(userRepository.save(userModel));
    }


}
