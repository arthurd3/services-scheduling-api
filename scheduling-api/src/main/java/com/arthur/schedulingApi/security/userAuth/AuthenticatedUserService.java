package com.arthur.schedulingApi.security.userAuth;

import com.arthur.schedulingApi.controllers.auth.loginResponseDTO.LoginResponseDTO;
import com.arthur.schedulingApi.controllers.auth.request.AuthRequestDTO;
import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.security.authSecurity.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticatedUserService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public User getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof UserAuthenticated userAuthenticated)) {
            throw new UserNotFoundException("Nenhum usuário autenticado encontrado ou o tipo da autenticação é inválido.");
        }

        return userAuthenticated.getUser();
    }

    public LoginResponseDTO login(AuthRequestDTO authDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.userName(), authDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var userAuthenticated = (UserAuthenticated) auth.getPrincipal();
        User userEntity = userAuthenticated.getUser();
        var token = tokenService.generateToken(userEntity);

        return new LoginResponseDTO("Login efetuado com Sucesso", token);
    }
}