package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.models.service.Services;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindServiceByIdTest {

    @InjectMocks
    private FindServiceById serviceFindById;

    @Mock
    private ServiceRepository svcRepository;

    @Nested
    class findServiceByIdAsDTO {

        @Test
        @DisplayName("Should find by id with success as DTO")
        void shouldFindByIdWithSuccess() {
            //ARRANGE

            var serviceId = 101L;
            Services serviceFromDb = createTestService();

            when(svcRepository.findById(serviceId)).thenReturn(Optional.of(serviceFromDb));

            //ACT

            var returnedService = serviceFindById.findById(serviceId);

            //ASSERT

            assertNotNull(returnedService);
            assertEquals(serviceFromDb.getName(), returnedService.name());
            assertEquals(serviceFromDb.getId(), returnedService.id());
            assertEquals(serviceFromDb.getCapacity(), returnedService.capacity());
            assertEquals(serviceFromDb.getDescription(), returnedService.description());
            assertEquals(serviceFromDb.getCreatedAt(), returnedService.createdAt());

            verify(svcRepository, times(1)).findById(serviceId);
        }

        @Test
        @DisplayName("Should Throw a ServiceNotFoundException")
        void shouldThrowServiceNotFoundException() {
            //ARRANGE
            var serviceId = 113L;

            when(svcRepository.findById(serviceId)).thenReturn(Optional.empty());

            //ACT

            ServiceNotFoundException exception = assertThrows(
                    ServiceNotFoundException.class, () -> serviceFindById.findById(serviceId));

            //ASSERT

            assertEquals("Servico com id "+ serviceId +" nao encontrado",exception.getMessage());

            verify(svcRepository, times(1)).findById(serviceId);
        }
    }

    @Nested
    class findServiceByIdAsModel {

        @Test
        @DisplayName("Should find by id with success as Model")
        void shouldFindByIdAsModel() {
            //ARRANGE
            var serviceId = 101L;
            Services serviceFromDb = createTestService();

            when(svcRepository.findById(serviceId)).thenReturn(Optional.of(serviceFromDb));

            //ACT

            var returnedService = serviceFindById.findByIdAsModel(serviceId);

            //ASSERT

            assertNotNull(returnedService);
            assertEquals(serviceFromDb.getName(), returnedService.getName());
            assertEquals(serviceFromDb.getId(), returnedService.getId());
            assertEquals(serviceFromDb.getCapacity(), returnedService.getCapacity());
            assertEquals(serviceFromDb.getDescription(), returnedService.getDescription());
            assertEquals(serviceFromDb.getCreatedAt(), returnedService.getCreatedAt());

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