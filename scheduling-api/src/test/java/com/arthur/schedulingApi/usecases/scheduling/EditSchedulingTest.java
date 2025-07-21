package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.controllers.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.usecases.EditScheduling;
import com.arthur.schedulingApi.usecases.FindScheduling;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditSchedulingTest {

    @InjectMocks
    private EditScheduling editScheduling;

    @Mock
    private FindScheduling findScheduling;

    @Nested
    class editScheduling {

        @Test
        @DisplayName("Should Edit Scheduling time")
        void shouldEditSchedulingTime() {
            var service = TestDataFactory.createTestService();
            var originalScheduling = TestDataFactory.createTestScheduling(service);
            Random random = new Random();
            LocalDateTime startTime = LocalDateTime.now()
                    .plusDays(random.nextInt(7) + 1)
                    .plusHours(random.nextInt(8) + 1)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);

            // LocalDateTime endTime = startTime.plusHours(1);

            var editDto = new SchedulingSlotRequestDTO(startTime , null);
            when(findScheduling.findSchedulingAsModel(originalScheduling.getId())).thenReturn(originalScheduling);

            SchedulingResponseDTO schedulingEdit = editScheduling.editSchedulingTime(originalScheduling.getId() , editDto);

            assertNotNull(schedulingEdit);
            assertEquals(startTime , schedulingEdit.startTime());
            assertEquals(originalScheduling.getId(), schedulingEdit.id());
            assertEquals(originalScheduling.getEndTime() , schedulingEdit.endTime());

        }


        @Test
        @DisplayName("Should Edit Scheduling time")
        void shouldThrowSchedulingNotFoundException() {
            var schedulingId = new Random().nextLong();

            Random random = new Random();
            LocalDateTime startTime = LocalDateTime.now()
                    .plusDays(random.nextInt(7) + 1)
                    .plusHours(random.nextInt(8) + 1)
                    .withMinute(0)
                    .withSecond(0)
                    .withNano(0);

            LocalDateTime endTime = startTime.plusHours(1);

            var editDto = new SchedulingSlotRequestDTO(startTime , endTime);

            when(findScheduling.findSchedulingAsModel(schedulingId))
                    .thenThrow(new SchedulingNotFoundException("Agendamento com id: "+ schedulingId +" Nao encontrado!!"));

            SchedulingNotFoundException exception = assertThrows(
                    SchedulingNotFoundException.class, () -> editScheduling.editSchedulingTime(schedulingId , editDto));

            assertEquals("Agendamento com id: "+ schedulingId +" Nao encontrado!!" , exception.getMessage());



        }

    }
}