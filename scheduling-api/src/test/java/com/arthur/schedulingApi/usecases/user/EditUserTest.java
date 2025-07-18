package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.models.user.UserRoles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EditUserTest {

    @InjectMocks
    private EditUser editUser;

    @Mock
    private FindUser findUser;

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
    }
}