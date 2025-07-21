package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.request.ServiceRequestDTO;
import com.arthur.schedulingApi.exceptions.UserNotAuthenticatedException;
import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.repositories.ServiceRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.usecases.CreateService;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @DisplayName("Should Throw a UserNotAuthenticatedException")
    void  shouldThrowUserNotAuthenticatedException() {
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
                .thenThrow(new UserNotAuthenticatedException("Nenhum usuário autenticado encontrado ou o tipo da autenticação é inválido."));

        //ACT

        UserNotAuthenticatedException exception = assertThrows(
                UserNotAuthenticatedException.class , () -> createService.createService(requestServiceDTO));


        //ASSERT
        assertEquals("Nenhum usuário autenticado encontrado ou o tipo da autenticação é inválido.", exception.getMessage());
    }

}