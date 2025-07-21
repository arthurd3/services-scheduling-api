package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.repositories.SchedulingRepository;
import com.arthur.schedulingApi.usecases.CreateScheduling;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
import com.arthur.schedulingApi.usecases.FindServiceById;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateSchedulingTest {
    @InjectMocks
    private CreateScheduling createScheduling;

    @Mock
    private FindServiceById findServiceById;

    @Mock
    private SchedulingRepository schedulingRepository;

    @Captor
    private ArgumentCaptor<Scheduling> schedulingCaptor;

    @Nested
    class createScheduling {

        @Test
        @DisplayName("Should create scheduling with success")
        void shouldCreateSchedulingWithSuccess() {
            var newService = TestDataFactory.createTestService();

            LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(14).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime endTime = LocalDateTime.now().plusDays(1).withHour(15).withMinute(0).withSecond(0).withNano(0);
            var schedulingCreate = new SchedulingSlotRequestDTO(startTime , endTime);

            when(findServiceById.findByIdAsModel(newService.getId())).thenReturn(newService);

            when(schedulingRepository.save(any(Scheduling.class))).thenAnswer(invocation -> {
                Scheduling schedulingToSave = invocation.getArgument(0);
                schedulingToSave.setId(301L);
                return schedulingToSave;
            });


            var schedulingResponse = createScheduling.createScheduling(schedulingCreate , newService.getId());


            verify(schedulingRepository).save(schedulingCaptor.capture());
            Scheduling capturedScheduling = schedulingCaptor.getValue();

            assertNotNull(capturedScheduling);
            assertEquals(startTime, capturedScheduling.getStartTime());
            assertEquals(endTime, capturedScheduling.getEndTime());
            assertEquals(newService, capturedScheduling.getServices());
            assertEquals(SchedulingStatus.AVAILABLE, capturedScheduling.getStatus());

            assertNotNull(schedulingResponse);
            assertEquals(301L, schedulingResponse.id());
            assertEquals(startTime, schedulingResponse.startTime());
        }


        @Test
        @DisplayName("Should Throw ServiceNotFoundException")
        void shouldThrowServiceNotFoundException() {
             var serviceId = 104L;

             when(findServiceById.findByIdAsModel(serviceId))
                     .thenThrow(new ServiceNotFoundException("Servico com id "+ serviceId +" nao encontrado"));

             ServiceNotFoundException exception = assertThrows(
                     ServiceNotFoundException.class, () -> createScheduling.createScheduling(null, serviceId));

             assertEquals("Servico com id "+ serviceId +" nao encontrado" , exception.getMessage());
        }

    }
}