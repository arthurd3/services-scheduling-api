package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.service.Services;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
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

    @Nested
    class findServiceByName {

        @Test
        @DisplayName("Should return a service by name with successful")
        void shouldFindServiceSuccessfully() {
            //ARRANGE

            var serviceFindName = "Consulta de Nutricao";

            Services serviceFromDb = TestDataFactory.createTestService();

            when(serviceRepository.findByName(serviceFindName)).thenReturn(Optional.of(serviceFromDb));

            //ACTION

            var returnedService = findServiceByName.findService(serviceFindName);


            //ASSERT

            assertNotNull(returnedService);
            assertEquals(serviceFromDb.getName(), returnedService.name());
            assertEquals(serviceFromDb.getId(), returnedService.id());
            assertEquals(serviceFromDb.getCapacity(), returnedService.capacity());
            assertEquals(serviceFromDb.getDescription(), returnedService.description());
            assertEquals(serviceFromDb.getCreatedAt(), returnedService.createdAt());

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

}