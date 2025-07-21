package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.request.UpdateStatusRequestDTO;
import com.arthur.schedulingApi.exceptions.SchedulingNotFoundException;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.usecases.ChangeStatusScheduling;
import com.arthur.schedulingApi.usecases.FindScheduling;
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
class ChangeStatusSchedulingTest {

    @InjectMocks
    private ChangeStatusScheduling changeStatusScheduling;

    @Mock
    private FindScheduling findScheduling;

    @Nested
    class changeStatus{

        @Test
        @DisplayName("Should Change Status With Success")
        void shouldChangeStatus() {
            var updateStatus = new UpdateStatusRequestDTO(SchedulingStatus.COMPLETED);

            var client = TestDataFactory.createTestUser();
            var newService = TestDataFactory.createTestService();
            var schedulingDb = TestDataFactory.createTestScheduling(newService);
            schedulingDb.setClient(client);
            var schedulingId = schedulingDb.getId();

            when(findScheduling.findSchedulingAsModel(schedulingId)).thenReturn(schedulingDb);

            var editedScheduling = changeStatusScheduling.changeStatus(updateStatus, schedulingId);

            assertNotNull(editedScheduling);

            assertEquals(updateStatus.newStatus(), editedScheduling.status());
            assertEquals(schedulingDb.getClient().getId(), editedScheduling.client().id());
            assertEquals(schedulingDb.getServices().getId(), editedScheduling.service().id());

        }

        @Test
        @DisplayName("Should Throw SchedulingNotFoundException")
        void shouldThrowSchedulingNotFoundException() {
            var schedulingId = 101L;
            var updateStatus = new UpdateStatusRequestDTO(SchedulingStatus.COMPLETED);

            when(findScheduling.findSchedulingAsModel(schedulingId))
                    .thenThrow(new SchedulingNotFoundException("Agendamento com id: "+ schedulingId +" Nao encontrado!!"));

            SchedulingNotFoundException exception = assertThrows(
                    SchedulingNotFoundException.class, () -> changeStatusScheduling.changeStatus(updateStatus , schedulingId));

            assertEquals("Agendamento com id: " + schedulingId + " Nao encontrado!!", exception.getMessage());
        }
    }
}