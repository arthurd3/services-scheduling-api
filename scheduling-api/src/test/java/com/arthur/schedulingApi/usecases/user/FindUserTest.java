package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
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
class FindUserTest {

    @InjectMocks
    private FindUser findUser;

    @Mock
    private UserRepository userRepository;


    @Nested
    class findUserDTO{

        @Test
        @DisplayName("Should Find User DTO with success")
        void shouldFindUserAsDto() {
            // ARRANGE
            var userIdFind = 1L;

            var userFounded = new User("Arthur" , "arthur_camposl@yahoo.com" , "312312312" ,"321312312452");
            userFounded.setId(1L);
            userFounded.setRole(UserRoles.USER);

            when(userRepository.findById(userIdFind)).thenReturn(Optional.of(userFounded));

            // ACT
            UserResponseDTO response = findUser.findUserAsDto(userIdFind);

            // ASSERT
            assertNotNull(response);
            assertEquals(userIdFind, response.id());
            assertEquals("arthur_camposl@yahoo.com", response.email());

            verify(userRepository, times(1)).findById(userIdFind);
        }


        @Test
        @DisplayName("Should Throw User NotFoundUserException on not success")
        void shouldThrowUserNotFoundException() {
            // ARRANGE
            var userIdFind = 1L;

            when(userRepository.findById(userIdFind)).thenReturn(Optional.empty());

            // ACT
            UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
                findUser.findUserAsDto(userIdFind);
            });

            // ASSERT

            assertEquals("Usuario com id "+ userIdFind +" nao encontrado!" , exception.getMessage());

            verify(userRepository, times(1)).findById(userIdFind);
        }

    }

    @Nested
    class findUser {

        @Test
        @DisplayName("Should Find User Entity with success")
        void shouldFindUserEntity() {
            //ARRANGE
            var userIdFind = 1L;

            User userSave = new User("Arthur" , "arthur_camposl@yahoo.com" , "312312312" ,"321312312452");

            when(userRepository.findById(userIdFind)).thenReturn(Optional.of(userSave));

            //ACT

            User userReturn = findUser.findUserEntity(userIdFind);

            // ASSERT

            assertNotNull(userReturn);
            assertEquals(userSave.getName() , userReturn.getName());
            assertEquals(userSave.getEmail() , userReturn.getEmail());

            verify(userRepository, times(1)).findById(userIdFind);
        }


        @Test
        void shouldThrowUserNotFoundException() {
            //ARRANGE
            var userIdFind = 1L;
            when(userRepository.findById(userIdFind)).thenReturn(Optional.empty());


            //ACT

            UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
                findUser.findUserEntity(userIdFind);
            });

            //ASSERT

            assertEquals("Usuario com id "+ userIdFind +" nao encontrado!" , exception.getMessage());

            verify(userRepository, times(1)).findById(userIdFind);
        }
    }
}