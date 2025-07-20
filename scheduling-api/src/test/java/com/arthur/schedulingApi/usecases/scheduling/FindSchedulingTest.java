package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.repositories.scheduling.SchedulingRepository;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
import org.hibernate.validator.constraints.ru.INN;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindSchedulingTest {

    @InjectMocks
    private FindScheduling findScheduling;

    @Mock
    private SchedulingRepository schedulingRepository;

    @Nested
    class findScheduling{

        @Test
        @DisplayName("Find Scheduling with success")
        void shouldFindSchedulingWithSuccess() {
            var client = TestDataFactory.createTestUser();
            var newService = TestDataFactory.createTestService();
            var newScheduling = TestDataFactory.createTestScheduling(newService);
            newScheduling.setClient(client);
            var schedulingId = newScheduling.getId();

            when(schedulingRepository.findById(schedulingId)).thenReturn(Optional.of(newScheduling));

            var schedulingFound = findScheduling.findScheduling(schedulingId);

            assertNotNull(schedulingFound);
            assertEquals(newScheduling.getClient().getId(), schedulingFound.client().id());
            assertEquals(newScheduling.getClient().getName(), schedulingFound.client().name());
            assertEquals(newScheduling.getStatus(), schedulingFound.status());
            assertEquals(newScheduling.getStartTime(), schedulingFound.startTime());
            assertEquals(newScheduling.getEndTime(), schedulingFound.endTime());
            assertEquals(newScheduling.getServices().getId() , schedulingFound.service().id());
        }

        @Test
        void shouldFindSchedulingAsModelWithSuccess() {
        }

    }
}