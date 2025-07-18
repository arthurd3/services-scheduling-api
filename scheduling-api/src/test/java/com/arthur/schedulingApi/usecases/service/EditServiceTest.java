package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.models.service.Services;
import com.arthur.schedulingApi.models.user.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EditServiceTest {

    @InjectMocks
    private EditService editService;

    @Mock
    private FindServiceById findServiceById;

    @Nested
    class editService{

        @Test
        @DisplayName("should edit service with success")
        void shouldEditService() {
            //ARRANGE

            long serviceId = 1L;
            var serviceDb = createTestService();

            var requestServiceDTO = new ServiceRequestDTO(
                    "Consulta Apenas",
                    3,
                    "https://example.com/images/nutricao.png",
                    "Avaliação completa e plano alimentar.",
                    "Jf",
                    new ArrayList<>(){}
            );

            when(findServiceById.findByIdAsModel(serviceId)).thenReturn(serviceDb);

            //ACT

            var editedService = editService.editService(serviceId, requestServiceDTO);

            //ASSERT
            
            assertNotNull(editedService);
            assertEquals(editedService.id() , serviceDb.getId());
            assertEquals("Consulta Apenas" , editedService.name());
            assertEquals(3 , editedService.capacity());
            assertEquals("Avaliação completa e plano alimentar." , editedService.description());

        }

    }


    @DisplayName("Create a fake Test User")
    private User createTestUser() {
        User user = new User();

        user.setId(1L);
        user.setName("Arthur - O Profissional");

        return user;
    }

    @DisplayName("Create a fake Test Service")
    private Services createTestService() {
        Services service = new Services();

        service.setId(101L);
        service.setName("Consulta de Nutrição");
        service.setCapacity(1);
        service.setDescription("Avaliação completa e plano alimentar personalizado.");
        service.setLocation("Atendimento Online via Google Meet");
        service.setUrl_image("https://example.com/images/nutricao.png");
        service.setOwner(createTestUser());
        service.setScheduling(new ArrayList<>());
        service.setCreatedAt(LocalDateTime.now());

        return service;
    }
}