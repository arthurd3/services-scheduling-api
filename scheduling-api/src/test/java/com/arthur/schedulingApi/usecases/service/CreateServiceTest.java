package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.service.Services;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.models.user.UserRoles;
import com.arthur.schedulingApi.repositories.services.ServiceRepository;
import com.arthur.schedulingApi.security.userAuth.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CreateServiceTest {

    @InjectMocks
    private CreateService createService;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private AuthenticatedUserService authenticatedUserService;

    @Captor
    private ArgumentCaptor<Services> servicesCaptor;

    @Test
    @DisplayName("Should create service with success")
    void shouldCreateService() {
        //ARRANGE

        var userAuth = TestDataFactory.createTestUser();
        var requestServiceDTO = new ServiceRequestDTO(
                "Consulta Apenas",
                3,
                "https://example.com/images/nutricao.png",
                "Avaliação completa e plano alimentar.",
                "Jf",
                new ArrayList<>(){}
        );

        when(authenticatedUserService.getAuthenticatedUser()).thenReturn(userAuth);

        when(serviceRepository.save(any(Services.class))).thenAnswer(invocation -> {
            Services serviceToSave = invocation.getArgument(0);
            serviceToSave.setId(101L);
            return serviceToSave;
        });

        var response = createService.createService(requestServiceDTO);


        verify(serviceRepository).save(servicesCaptor.capture());
        Services capturedService = servicesCaptor.getValue();


        assertNotNull(capturedService);
        assertEquals("Consulta Apenas", capturedService.getName());
        assertEquals("Avaliação completa e plano alimentar.", capturedService.getDescription());
        assertEquals(userAuth, capturedService.getOwner());

        assertNotNull(response);
        assertEquals(101L, response.id());
        assertEquals("Consulta Apenas", response.name());

    }

    @Test
    @DisplayName("Should Throw a UserNotFoundException")
    void  shouldThrowUserNotFoundException() {
        //ARRANGE
        var requestServiceDTO = new ServiceRequestDTO(
                "Consulta Apenas",
                3,
                "https://example.com/images/nutricao.png",
                "Avaliação completa e plano alimentar.",
                "Jf",
                new ArrayList<>(){}
        );

        when(authenticatedUserService.getAuthenticatedUser())
                .thenThrow(new UserNotFoundException("O Usuario precisa estar autenticado"));

        //ACT

        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class , () -> createService.createService(requestServiceDTO));


        //ASSERT
        assertEquals("O Usuario precisa estar autenticado", exception.getMessage());
    }




}