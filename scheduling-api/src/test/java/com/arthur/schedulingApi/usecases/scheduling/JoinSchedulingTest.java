package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.models.scheduling.Scheduling;
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
    private JoinScheduling createService;

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
            var newScheduling = TestDataFactory.createTestScheduling(newService);

            when(findScheduling.findSchedulingAsModel(schedulingIdJoin)).thenReturn(newScheduling);

            //ACT



        }
    }
}