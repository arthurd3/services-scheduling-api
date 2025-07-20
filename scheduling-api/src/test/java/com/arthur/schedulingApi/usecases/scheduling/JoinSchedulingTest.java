package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.exceptions.SchedulingNotAvailableException;
import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.exceptions.UserNotAuthenticatedException;
import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.scheduling.SchedulingStatus;
import com.arthur.schedulingApi.security.userAuth.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JoinSchedulingTest {

    @InjectMocks
    private JoinScheduling joinScheduling;

    @Mock
    private AuthenticatedUserService authenticatedUserService;

    @Mock
    private FindScheduling findScheduling;



    @Nested
    class joinSchedulingTest{

        @Test
        @DisplayName("Should join Scheduling with success")
        void shouldJoinSchedulingWithSuccess() {
            //ARRANGE
            var schedulingIdJoin = 201L;

            var newService = TestDataFactory.createTestService();

            var user = TestDataFactory.createRandomUserTest();

            var newScheduling = TestDataFactory.createTestScheduling(newService);

            when(findScheduling.findSchedulingAsModel(schedulingIdJoin)).thenReturn(newScheduling);
            when(authenticatedUserService.getAuthenticatedUser()).thenReturn(user);

            //ACT

            var schedulingResponse = joinScheduling.joinScheduling(schedulingIdJoin);

            //ASSERTION

            assertNotNull(schedulingResponse);

            assertEquals(schedulingResponse.client().id() , user.getId());
            assertEquals(SchedulingStatus.BOOKED , schedulingResponse.status());
            assertEquals(schedulingResponse.service().id() , newService.getId());
        }


        @Test
        @DisplayName("Should Throw SchedulingNotAvailableException with scheduling is booked")
        void shouldThrowSchedulingNotAvailableException() {

            //ARRANGE
            var schedulingIdJoin = 201L;

            var newService = TestDataFactory.createTestService();

            var user = TestDataFactory.createRandomUserTest();

            var newScheduling = TestDataFactory.createTestScheduling(newService);
            newScheduling.setStatus(SchedulingStatus.BOOKED);

            when(findScheduling.findSchedulingAsModel(schedulingIdJoin)).thenReturn(newScheduling);
            when(authenticatedUserService.getAuthenticatedUser()).thenReturn(user);

            //ACT

            SchedulingNotAvailableException exception = assertThrows(
                    SchedulingNotAvailableException.class,() -> joinScheduling.joinScheduling(schedulingIdJoin));

            //ASSERTION

            assertEquals("Este horário não está mais disponível.",exception.getMessage());

        }


        @Test
        @DisplayName("Should Throw UserNotFoundException with user is not authenticated")
        void shouldThrowUserNotFoundException() {

            //ARRANGE
            var schedulingIdJoin = 201L;

            when(authenticatedUserService.getAuthenticatedUser())
                    .thenThrow(new UserNotAuthenticatedException("Nenhum usuário autenticado encontrado ou o tipo da autenticação é inválido."));

            //ACT

            UserNotAuthenticatedException exception = assertThrows(
                    UserNotAuthenticatedException.class,() -> joinScheduling.joinScheduling(schedulingIdJoin));

            //ASSERTION

            assertEquals("Nenhum usuário autenticado encontrado ou o tipo da autenticação é inválido.",exception.getMessage());

        }
    }
}