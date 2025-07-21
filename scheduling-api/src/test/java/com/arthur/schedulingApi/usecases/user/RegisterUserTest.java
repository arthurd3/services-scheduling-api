package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.request.UserRequestDTO;
import com.arthur.schedulingApi.exceptions.EmailAlreadyExistsException;
import com.arthur.schedulingApi.exceptions.PhoneAlreadyExistsException;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.models.user.UserRoles;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Nested
    class registerUser{

        @Test
        @DisplayName("Should a exception on create user with the already exists e-mail")
        void shouldCreateUserEmailException() {
            var userRequest = new UserRequestDTO("Arthur", "arthur@email.com", "1234", "321312321312");

            when(userRepository.existsByEmail("arthur@email.com")).thenReturn(true);

            EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> {
                registerUser.registerUser(userRequest);
            });

            assertEquals("O E-mail 'arthur@email.com' já está em uso.", exception.getMessage());

            verify(userRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should a exception on create user with the already exists phone")
        void shouldCreateUserPhoneException() {

            var userRequest = new UserRequestDTO("Arthur", "arthur@email.com", "1234", "321312321312");

            when(userRepository.existsByPhoneNumber("321312321312")).thenReturn(true);

            PhoneAlreadyExistsException exception = assertThrows(PhoneAlreadyExistsException.class, () -> {
                registerUser.registerUser(userRequest);
            });

            assertEquals("O Telefone '321312321312' já está em uso.",exception.getMessage());

            verify(userRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should create a user if it contains an email or phone number not used")
        void shouldCreateUser() {

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

            verify(userRepository).save(userCaptor.capture());

            User capturedUser = userCaptor.getValue();

            assertEquals("senha_criptografada", capturedUser.getPassword());
            assertEquals(UserRoles.USER, capturedUser.getRole());
            assertEquals("Arthur", capturedUser.getName());

            assertNotNull(response);
            assertEquals(1L, response.id());
        }
    }


}