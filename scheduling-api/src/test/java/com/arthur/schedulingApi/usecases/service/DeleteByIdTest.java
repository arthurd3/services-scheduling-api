package com.arthur.schedulingApi.usecases.service;

import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.repositories.ServiceRepository;
import com.arthur.schedulingApi.usecases.DeleteById;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


}