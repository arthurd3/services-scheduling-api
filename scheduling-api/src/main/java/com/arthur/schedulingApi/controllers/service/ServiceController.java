package com.arthur.schedulingApi.controllers.service;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.usecases.service.CreateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    private final CreateService createService;

    public ServiceController(CreateService createService) {
        this.createService = createService;
    }


    @PostMapping("/create/{ownerId}")
    public ResponseEntity<Optional<ServiceResponseDTO>> createService(@PathVariable(name = "ownerId") Long ownerId , @RequestBody ServiceRequestDTO serviceRequestDTO){
        return ResponseEntity.ok(createService.createService(ownerId,serviceRequestDTO));
    }


}
