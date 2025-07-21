package com.arthur.schedulingApi.security.jwt;

import com.arthur.schedulingApi.controllers.response.LoginResponseDTO;
import com.arthur.schedulingApi.controllers.request.AuthRequestDTO;
import com.arthur.schedulingApi.exceptions.UserNotAuthenticatedException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.security.authSecurity.UserAuthenticated;
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
            throw new UserNotAuthenticatedException("Nenhum usuário autenticado encontrado ou o tipo da autenticação é inválido.");
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