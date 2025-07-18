package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.service.Services;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindServiceByNameTest {

    @InjectMocks
    private FindServiceByName findServiceByName;

    @Mock
    private ServiceRepository serviceRepository;

    @Captor
    private ArgumentCaptor<Services> serviceCaptor;

    @Nested
    class findServiceByName {

        @Test
        @DisplayName("Should return a service by name with successful")
        void shouldFindServiceSuccessfully() {
            //ARRANGE

            var serviceFindName = "Consulta de Nutricao";

            Services serviceFromDb = createTestService();

            when(serviceRepository.findByName(serviceFindName)).thenReturn(Optional.of(serviceFromDb));

            //ACTION

            var returnedService = findServiceByName.findService(serviceFindName);


            //ASSERT

            assertTrue(returnedService.isPresent());
            assertEquals(serviceFromDb.getName(), returnedService.get().name());
            assertEquals(serviceFromDb.getId(), returnedService.get().id());
            assertEquals(serviceFromDb.getCapacity(), returnedService.get().capacity());
            assertEquals(serviceFromDb.getDescription(), returnedService.get().description());
            assertEquals(serviceFromDb.getCreatedAt(), returnedService.get().createdAt());

        }

        @Test
        @DisplayName("Should Throw a ServiceNotFoundException")
        void shouldThrowServiceNotFoundException() {
            //ARRANGE
            var serviceFindName = "Consulta de Nutricao";

            when(serviceRepository.findByName(serviceFindName)).thenReturn(Optional.empty());

            //ACTION
            ServiceNotFoundException exception = assertThrows(
                    ServiceNotFoundException.class, () -> findServiceByName.findService(serviceFindName));


            //ASSERT
            assertEquals("Servico com nome " + serviceFindName + " não encontrado.", exception.getMessage());
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