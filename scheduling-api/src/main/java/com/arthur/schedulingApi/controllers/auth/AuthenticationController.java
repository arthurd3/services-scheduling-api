package com.arthur.schedulingApi.controllers.auth;

import com.arthur.schedulingApi.controllers.auth.loginResponseDTO.LoginResponseDTO;
import com.arthur.schedulingApi.controllers.auth.request.AuthRequestDTO;
import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.security.authSecurity.TokenService;
import com.arthur.schedulingApi.security.userAuth.UserAuthenticated;
import com.arthur.schedulingApi.usecases.user.RegisterUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final RegisterUser registerUser;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, RegisterUser registerUser, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.registerUser = registerUser;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthRequestDTO authDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.userName() ,  authDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var userAuthenticated = (UserAuthenticated) auth.getPrincipal();
        User userEntity = userAuthenticated.getUser();
        var token = tokenService.generateToken(userEntity);

        return ResponseEntity.ok(new LoginResponseDTO("Login efetuado com Sucesso" , token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(registerUser.registerUser(userRequestDTO));
    }
}
