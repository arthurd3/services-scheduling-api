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
class DeleteByIdTest {

    @InjectMocks
    private DeleteById deleteById;

    @Mock
    private ServiceRepository serviceRepository;


    @Nested
    class deleteServiceById{

        @Test
        @DisplayName("Should by delete success.")
        void shouldDeleteByIdSuccess() {

            //ARRANGE
            var serviceId = 101L;

            when(serviceRepository.existsById(serviceId)).thenReturn(true);

            //ACT
            deleteById.deleteById(serviceId);


            //ASSERT
            verify(serviceRepository, times(1)).deleteById(serviceId);

        }



        @Test
        @DisplayName("Should by delete success.")
        void shouldThrowExceptionWhenServiceNotFound() {
            //ARRANGE
            var serviceId = 101L;

            when(serviceRepository.existsById(serviceId)).thenReturn(false);

            //ACT
            ServiceNotFoundException exception = assertThrows(
                    ServiceNotFoundException.class, () -> deleteById.deleteById(serviceId));

            //ASSERT
            assertEquals("Não é possível deletar: Serviço com id " + serviceId + " não encontrado.", exception.getMessage());
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