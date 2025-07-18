package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        void findUserAsDto() {
            // ARRANGE
            var userIdFind = 1L;

            var userFounded = new User("Arthur" , "arthur_camposl@yahoo.com" , "312312312" ,"321312312452");
            userFounded.setId(1L);

            when(userRepository.findById(userIdFind)).thenReturn(Optional.of(userFounded));

            // ACT
            UserResponseDTO response = findUser.findUserAsDto(userIdFind);

            // ASSERT
            assertNotNull(response);
            assertEquals(userIdFind, response.id());
            assertEquals("arthur_camposl@yahoo.com", response.email());

            verify(userRepository, times(1)).findById(userIdFind);
        }

    }

    @Nested
    class findUser {

        @Test
        void findUserEntity() {
        }
    }
}