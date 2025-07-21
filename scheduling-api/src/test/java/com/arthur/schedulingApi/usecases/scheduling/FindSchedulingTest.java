package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import com.arthur.schedulingApi.usecases.FindScheduling;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
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
    class findSchedulingResponse{

        @Test
        @DisplayName("Should Find Scheduling Response with success")
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
        @DisplayName("Should Throw SchedulingNotFoundException on find Scheduling Response")
        void shouldThrowSchedulingNotFoundException() {

            var schedulingId = 101L;

            when(schedulingRepository.findById(schedulingId)).thenReturn(Optional.empty());

            SchedulingNotFoundException exception =  assertThrows(
                    SchedulingNotFoundException.class, () -> findScheduling.findScheduling(schedulingId));

            assertNotNull(exception);
            assertEquals("Agendamento com id: "+ schedulingId +" Nao encontrado!!" , exception.getMessage());
        }
    }

    @Nested
    class findSchedulingEntity{

        @Test
        @DisplayName("Should Find Scheduling Entity with success")
        void shouldFindSchedulingAsModelWithSuccess() {
            var client = TestDataFactory.createTestUser();
            var newService = TestDataFactory.createTestService();
            var newScheduling = TestDataFactory.createTestScheduling(newService);
            newScheduling.setClient(client);
            var schedulingId = newScheduling.getId();

            when(schedulingRepository.findById(schedulingId)).thenReturn(Optional.of(newScheduling));

            var schedulingFound = findScheduling.findSchedulingAsModel(schedulingId);

            assertNotNull(schedulingFound);
            assertEquals(newScheduling.getClient().getId(), schedulingFound.getClient().getId());
            assertEquals(newScheduling.getClient().getName(), schedulingFound.getClient().getName());
            assertEquals(newScheduling.getStatus(), schedulingFound.getStatus());
            assertEquals(newScheduling.getStartTime(), schedulingFound.getStartTime());
            assertEquals(newScheduling.getEndTime(), schedulingFound.getEndTime());
            assertEquals(newScheduling.getServices().getId() , schedulingFound.getServices().getId());
        }

        @Test
        @DisplayName("Should Throw SchedulingNotFoundException on find Scheduling Entity")
        void shouldThrowSchedulingNotFoundException() {

            var schedulingId = 101L;

            when(schedulingRepository.findById(schedulingId)).thenReturn(Optional.empty());

            SchedulingNotFoundException exception =  assertThrows(
                    SchedulingNotFoundException.class, () -> findScheduling.findSchedulingAsModel(schedulingId));

            assertNotNull(exception);
            assertEquals("Agendamento com id: "+ schedulingId +" Nao encontrado!!" , exception.getMessage());
        }
    }
}