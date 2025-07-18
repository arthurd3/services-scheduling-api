package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.user.User;
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
class FindUserByNameTest {

    @InjectMocks
    private FindUserByName findUserByName;

    @Mock
    private UserRepository userRepository;


    @Nested
    class findUser{

        @Test
        @DisplayName("Should Find User with success")
        void shouldFindUserWithSuccess() {

            String searchName = "Arthur";

            User foundUser = new User("Arthur", "arthur@email.com", "123456789", "31312321321");

            when(userRepository.findByName(searchName)).thenReturn(Optional.of(foundUser));

            User response = findUserByName.findUserByName(searchName);

            assertNotNull(response);
            assertEquals("Arthur", response.getName());
            assertEquals("arthur@email.com", response.getEmail());

            verify(userRepository, times(1)).findByName(searchName);
        }


        @Test
        @DisplayName("Should a Not Found Exception on Find User with not exists")
        void shouldFindUserNotFoundException() {

            String searchName = "Arthur";

            when(userRepository.findByName(searchName)).thenReturn(Optional.empty());

            UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
                findUserByName.findUserByName(searchName);
            });

            assertEquals("Usuario com nome "+ searchName +" nao encontrado!",exception.getMessage());

            verify(userRepository, times(1)).findByName(searchName);
        }



    }



}