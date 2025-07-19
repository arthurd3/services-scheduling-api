package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.security.userAuth.AuthenticatedUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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

            var schedulingIdFind = 1L;




        }
    }
}