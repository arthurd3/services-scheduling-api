package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.request.UserRequestDTO;
import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.UserRoles;
import com.arthur.schedulingApi.repositories.UserRepository;
import com.arthur.schedulingApi.usecases.EditUser;
import com.arthur.schedulingApi.usecases.FindUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

            assertNotNull(editedUser);

            assertEquals(1L , editedUser.id() );
            assertEquals("Arthur Claudio" , editedUser.name() );
            assertEquals("arthur_camposl@yahoo.com" , editedUser.email() );
            assertEquals("321312321" , editedUser.phoneNumber() );

        }


        @Test
        @DisplayName("Should UserNotFoundException error")
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