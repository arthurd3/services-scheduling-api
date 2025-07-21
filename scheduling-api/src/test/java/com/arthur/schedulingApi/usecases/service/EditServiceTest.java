package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.controllers.request.ServiceRequestDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.usecases.factory.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

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
            var serviceDb = TestDataFactory.createTestService();

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

        @Test
        @DisplayName("Should Throw ServiceNotFoundException on edit not exist service")
        void shouldThrowServiceNotFoundException() {
            //ARRANGE

            var serviceId = 142L;

            when(findServiceById.findByIdAsModel(serviceId))
                    .thenThrow(new ServiceNotFoundException("Servico com id "+ serviceId +" nao encontrado"));

            var requestServiceDTO = new ServiceRequestDTO(
                    "Consulta Apenas",
                    3,
                    "https://example.com/images/nutricao.png",
                    "Avaliação completa e plano alimentar.",
                    "Jf",
                    new ArrayList<>(){}
            );

            //ACT

            Exception exception = assertThrows(ServiceNotFoundException.class,
                    () -> editService.editService(serviceId, requestServiceDTO ));


            //ASSERT

            assertNotNull(exception);

            assertEquals("Servico com id "+ serviceId +" nao encontrado", exception.getMessage());

        }

    }


}