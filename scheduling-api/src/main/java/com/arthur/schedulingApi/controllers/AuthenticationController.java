package com.arthur.schedulingApi.controllers;

import com.arthur.schedulingApi.controllers.response.LoginResponseDTO;
import com.arthur.schedulingApi.controllers.request.AuthRequestDTO;
import com.arthur.schedulingApi.controllers.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.RegisterUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticatedUserService authenticationUseCase;
    private final RegisterUser registerUser;


    @ResponseStatus(OK)
    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody AuthRequestDTO authDTO) {
        return authenticationUseCase.login(authDTO);
    }

    @ResponseStatus(OK)
    @PostMapping("/register")
    public UserResponseDTO registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return registerUser.registerUser(userRequestDTO);
    }
}
