package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.exceptions.EmailAlreadyExistsException;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterUserTest {

    @InjectMocks
    private RegisterUser registerUser;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    void deveLancarExcecao_QuandoEmailJaExistir() {

        var userRequest = new UserRequestDTO("Arthur", "arthur@email.com", "1234", "321312321312");


        when(userRepository.existsByEmail("arthur@email.com")).thenReturn(true);

        EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> {
            registerUser.registerUser(userRequest);
        });

        assertEquals("O E-mail 'arthur@email.com' já está em uso.", exception.getMessage());

        verify(userRepository, never()).save(any());
    }
}