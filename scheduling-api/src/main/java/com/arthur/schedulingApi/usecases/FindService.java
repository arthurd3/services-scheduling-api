package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.ServiceToResponse.serviceToResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindService {

    private final ServiceRepository serviceRepository;

    @Cacheable(value = "SERVICE_CACHE", key = "#id")
    public ServiceResponseDTO findById(Long id){
        log.info("### Buscando serviço com ID {} no BANCO DE DADOS... ###", id);
        var findService = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Servico com id "+ id +" nao encontrado"));
        return serviceToResponse(findService);
    }

    public Services findByIdAsModel(Long id){

        return serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Servico com id "+ id +" nao encontrado"));
    }
}