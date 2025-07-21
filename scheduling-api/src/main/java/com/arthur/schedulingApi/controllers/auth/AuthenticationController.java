package com.arthur.schedulingApi.controllers.auth;

import com.arthur.schedulingApi.controllers.auth.loginResponseDTO.LoginResponseDTO;
import com.arthur.schedulingApi.controllers.auth.request.AuthRequestDTO;
import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.security.userAuth.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.user.RegisterUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticatedUserService authenticationUseCase;
    private final RegisterUser registerUser;

    public AuthenticationController(AuthenticatedUserService authenticationUseCase, RegisterUser registerUser) {
        this.authenticationUseCase = authenticationUseCase;
        this.registerUser = registerUser;
    }

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
