package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindUserByNameTest {

    @InjectMocks
    private FindUserByName findUserByName;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userCaptor;


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
    }



}