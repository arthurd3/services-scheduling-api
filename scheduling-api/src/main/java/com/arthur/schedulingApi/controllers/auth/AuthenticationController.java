package com.arthur.schedulingApi.controllers.auth;

import com.arthur.schedulingApi.controllers.auth.request.AuthRequestDTO;
import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.usecases.user.RegisterUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final RegisterUser registerUser;

    public AuthenticationController(AuthenticationManager authenticationManager, RegisterUser registerUser) {
        this.authenticationManager = authenticationManager;
        this.registerUser = registerUser;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequestDTO authDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.userName() ,  authDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(registerUser.registerUser(userRequestDTO));
    }
}
