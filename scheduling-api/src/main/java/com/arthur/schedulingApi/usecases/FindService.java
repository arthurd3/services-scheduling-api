package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.repositories.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.usecases.mapper.ServiceToResponse.serviceToResponse;

@Service
@Slf4j
public class FindService {

    private final ServiceRepository serviceRepository;

    public FindService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Cacheable(value = "SERVICE_CACHE", key = "#serviceId", unless = "#result == null")
    public ServiceResponseDTO findById(Long serviceId){
        log.info("Buscando serviço com ID {} no BANCO DE DADOS", serviceId);
        var findService = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new ServiceNotFoundException("Servico com id "+ serviceId +" nao encontrado"));
        return serviceToResponse(findService);
    }

    public Services findByIdAsModel(Long id){
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Servico com id "+ id +" nao encontrado"));
    }
}