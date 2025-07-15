package com.arthur.schedulingApi.controllers.auth;

import com.arthur.schedulingApi.controllers.auth.loginResponseDTO.LoginResponseDTO;
import com.arthur.schedulingApi.controllers.auth.request.AuthRequestDTO;
import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.security.userAuth.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.user.RegisterUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticatedUserService authenticationUseCase;
    private final RegisterUser registerUser;

    public AuthenticationController(AuthenticatedUserService authenticationUseCase, RegisterUser registerUser) {
        this.authenticationUseCase = authenticationUseCase;
        this.registerUser = registerUser;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthRequestDTO authDTO) {
        var loginResponse = authenticationUseCase.login(authDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(registerUser.registerUser(userRequestDTO));
    }
}
