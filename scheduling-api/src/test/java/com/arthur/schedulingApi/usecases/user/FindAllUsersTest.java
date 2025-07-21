package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.users.UserRepository;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllUsersTest {

    @InjectMocks
    private FindAllUsers findAllUsers;

    @Mock
    private UserRepository userRepository;

    @Nested
    class findAllUsers{

        @Test
        @DisplayName("Should find pageable list users")
        void shouldReturnPageableUsers() {

            //ARRANGE
            var pageRequest = PageRequest.of(0, 5);

            List<User> userList = TestDataFactory.createdListOfUsers();

            List<User> pageContent = userList.subList(0, 5);

            Page<User> fakePage = new PageImpl<>(pageContent, pageRequest, userList.size());

            when(userRepository.findAll(pageRequest)).thenReturn(fakePage);


            // ACTION
            Page<UserResponseDTO> foundPage = findAllUsers.findAllUsers(pageRequest);

            // ASSERT
            assertNotNull(foundPage);
            assertEquals(5, foundPage.getSize());
            assertEquals(6, foundPage.getTotalElements());
            assertEquals(2, foundPage.getTotalPages());
            assertEquals("Carlos Silva", foundPage.getContent().get(0).name());
            assertEquals("Juliana Alves", foundPage.getContent().get(4).name());

        }

        @Test
        @DisplayName("Should return nothing users")
        void shouldReturnNothingUsers() {
            //ARRANGE
            var pageRequest = PageRequest.of(0, 5);

            Page<User> emptyPage = Page.empty();

            when(userRepository.findAll(pageRequest)).thenReturn(emptyPage);

            // ACTION
            Page<UserResponseDTO> foundPage = findAllUsers.findAllUsers(pageRequest);

            // ASSERT
            assertNotNull(foundPage);
            assertTrue(foundPage.isEmpty());
            assertEquals(0, foundPage.getTotalElements());
            assertEquals(0, foundPage.getContent().size());
        }
    }


}