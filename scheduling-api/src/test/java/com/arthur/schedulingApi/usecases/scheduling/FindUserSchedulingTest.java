package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.scheduling.SchedulingRepository;
import com.arthur.schedulingApi.security.userAuth.AuthenticatedUserService;
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
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindUserSchedulingTest {

    @InjectMocks
    private FindUserScheduling findUserScheduling;

    @Mock
    private SchedulingRepository schedulingRepository;

    @Mock
    private AuthenticatedUserService authenticatedUserService;


    @Nested
    class findUserScheduling {

        @Test
        @DisplayName("Should find User Schedulings")
        void shouldFindUserSchedulings() {
            // ARRANGE
            var client = TestDataFactory.createTestUser();
            Pageable pageable = PageRequest.of(0, 5);


            var allSchedulings = TestDataFactory.createListOfSchedulings();


            int toIndex = Math.min(allSchedulings.size(), 5);
            List<Scheduling> pageContent = allSchedulings.subList(0, toIndex);


            Page<Scheduling> fakePage = new PageImpl<>(pageContent, pageable, allSchedulings.size());

            when(authenticatedUserService.getAuthenticatedUser()).thenReturn(client);
            when(schedulingRepository.findByClientId(client.getId(), pageable)).thenReturn(fakePage);

            // ACT
            Page<SchedulingResponseDTO> foundPage = findUserScheduling.findUserScheduling(pageable);

            // ASSERT
            assertNotNull(foundPage);
            assertEquals(allSchedulings.size(), foundPage.getTotalElements());
            assertEquals(pageContent.size(), foundPage.getNumberOfElements());
        }
    }
}