package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import com.arthur.schedulingApi.usecases.DeleteScheduling;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteSchedulingTest {

    @InjectMocks
    private DeleteScheduling deleteScheduling;

    @Mock
    private SchedulingRepository schedulingRepository;

    @Nested
    class deleteScheduling {

        @Test
        @DisplayName("Should delete Scheduling with success")
        void shouldDeleteSchedulingWithSuccess() {

            var client = TestDataFactory.createTestUser();
            var newService = TestDataFactory.createTestService();
            var newScheduling = TestDataFactory.createTestScheduling(newService);
            newScheduling.setClient(client);
            var schedulingId = newScheduling.getId();

            when(schedulingRepository.existsById(schedulingId)).thenReturn(true);

            deleteScheduling.deleteScheduling(schedulingId);

            verify(schedulingRepository, times(1)).deleteById(schedulingId);
        }


        @Test
        @DisplayName("Should Throw SchedulingNotFoundException ")
        void shouldThrowSchedulingNotFoundException() {
            var schedulingId = 101L;

            when(schedulingRepository.existsById(schedulingId)).thenReturn(false);

            SchedulingNotFoundException exception = assertThrows(
                    SchedulingNotFoundException.class, () -> deleteScheduling.deleteScheduling(schedulingId));

            assertEquals("Nao foi Possivel deletar o agendamento com id " + schedulingId + " nao existe!!", exception.getMessage());

            verify(schedulingRepository, times(0)).deleteById(schedulingId);
        }
    }
}