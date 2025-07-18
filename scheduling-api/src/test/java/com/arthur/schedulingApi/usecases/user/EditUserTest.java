package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.models.user.UserRoles;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditUserTest {

    @InjectMocks
    private EditUser editUser;

    @Mock
    private FindUser findUser;

    @Mock
    private UserRepository userRepository;

    @Nested
    class editUser{

        @Test
        @DisplayName("Should edit user")
        void shouldEditUser() {

            //ARRANGE

            long userIdToEdit = 1L;
            var userDTOEdit = new UserRequestDTO("Arthur Claudio" , "arthur_camposl@yahoo.com" , "32131231" , "321312321");

            var userOriginal = new User("Arthur" , "arthur_camposl@yahoo.com" , "32131231" , "321312321");
            userOriginal.setId(1L);
            userOriginal.setRole(UserRoles.USER);

            when(findUser.findUserEntity(userIdToEdit)).thenReturn(userOriginal);

            //ACT

            var editedUser = editUser.editUser(userIdToEdit , userDTOEdit);

            //ASSERT

            assertTrue(editedUser.isPresent());

            assertEquals(1L , editedUser.get().id() );
            assertEquals("Arthur Claudio" , editedUser.get().name() );
            assertEquals("arthur_camposl@yahoo.com" , editedUser.get().email() );
            assertEquals("321312321" , editedUser.get().phoneNumber() );

        }


        @Test
        @DisplayName("Shoul UserNotFoundException erro")
        void shouldUserNotFoundException() {

            // ARRANGE
            long userIdInexistente = 99L;
            var userDTO = new UserRequestDTO("Nome Novo", "email@novo.com", "senha", "12345");


            when(findUser.findUserEntity(userIdInexistente))
                    .thenThrow(new UserNotFoundException("Usuario com id " + userIdInexistente + " nao encontrado!"));

            // ACT
            UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
                editUser.editUser(userIdInexistente, userDTO);
            });

            //ASSERT
            assertEquals("Usuario com id " + userIdInexistente + " nao encontrado!", exception.getMessage());
        }
    }
}