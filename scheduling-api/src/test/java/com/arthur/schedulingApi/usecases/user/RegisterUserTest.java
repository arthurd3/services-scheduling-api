package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.exceptions.EmailAlreadyExistsException;
import com.arthur.schedulingApi.exceptions.PhoneAlreadyExistsException;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import org.junit.jupiter.api.Nested;
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

    @Nested
    class registerUser{

        @Test
        void deveLancarExcecaoQuandoEmailJaExistir() {

            var userRequest = new UserRequestDTO("Arthur", "arthur@email.com", "1234", "321312321312");

            when(userRepository.existsByEmail("arthur@email.com")).thenReturn(true);

            EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> {
                registerUser.registerUser(userRequest);
            });

            assertEquals("O E-mail 'arthur@email.com' já está em uso.", exception.getMessage());

            verify(userRepository, never()).save(any());
        }
        
        @Test
        void deveLancarExcecaoQuandoTelefoneJaExistir() {

            var userRequest = new UserRequestDTO("Arthur", "arthur@email.com", "1234", "321312321312");

            when(userRepository.existsByPhoneNumber("321312321312")).thenReturn(true);

            PhoneAlreadyExistsException exception = assertThrows(PhoneAlreadyExistsException.class, () -> {
                registerUser.registerUser(userRequest);
            });

            assertEquals("O Telefone '321312321312' já está em uso.",exception.getMessage());

            verify(userRepository, never()).save(any());
        }

        @Test
        void deveRegistrarUsuarioComSucesso_QuandoDadosSaoUnicos() {
            var requestDTO = new UserRequestDTO("Arthur", "arthur@email.com", "senha123", "1199998888");

            when(userRepository.existsByEmail(requestDTO.email())).thenReturn(false);
            when(userRepository.existsByPhoneNumber(requestDTO.phoneNumber())).thenReturn(false);
            when(passwordEncoder.encode("senha123")).thenReturn("senha_criptografada");


            when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
                User userToSave = invocation.getArgument(0);
                userToSave.setId(1L);
                return userToSave;
            });

            var response = registerUser.registerUser(requestDTO);

            assertNotNull(response);
            assertEquals(1L, response.id());
            assertEquals("Arthur", response.name());
            verify(userRepository).save(any(User.class));
        }
    }
}