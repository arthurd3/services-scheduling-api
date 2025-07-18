package com.arthur.schedulingApi.usecases.user;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserTest {

    @InjectMocks
    private DeleteUser deleteUser;

    @Mock
    private UserRepository userRepository;


    @Nested
    class deleteById{

        @Test
        @DisplayName("Should delete user by ID with success")
        void shouldDeleteById(){
            //ARRANGE
            var userDeleteId = 1L;

            when(userRepository.existsById(userDeleteId)).thenReturn(true);

            //ACTION
            deleteUser.deleteUser(userDeleteId);

            //ASSERTION

            verify(userRepository, times(1)).deleteById(userDeleteId);
        }

        @Test
        @DisplayName("Should Throw a UserNotFound excption")
        void shouldThrowUserNotFoundOnDeleteById(){
            //ARRANGE
            var userDeleteId = 1L;

            when(userRepository.existsById(userDeleteId)).thenReturn(false);

            //ACTION
            UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                    () -> deleteUser.deleteUser(userDeleteId));


            //ASSERT

            assertEquals("Nao foi possivel deletar o Usuario com id " + userDeleteId, exception.getMessage());
        }

    }
}