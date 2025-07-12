package com.arthur.schedulingApi.controllers.service;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.usecases.service.CreateService;
import com.arthur.schedulingApi.usecases.service.FindServiceById;
import com.arthur.schedulingApi.usecases.service.FindServiceByName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    private final CreateService createService;
    private final FindServiceByName findServiceByName;
    private final FindServiceById findServiceById;

    public ServiceController(CreateService createService, FindServiceByName findServiceByName, FindServiceById findServiceById) {
        this.createService = createService;
        this.findServiceByName = findServiceByName;
        this.findServiceById = findServiceById;
    }

    @PostMapping("/create/{ownerId}")
    public ResponseEntity<Optional<ServiceResponseDTO>> createService(@PathVariable(name = "ownerId") Long ownerId , @RequestBody ServiceRequestDTO serviceRequestDTO){
        return ResponseEntity.ok(createService.createService(ownerId,serviceRequestDTO));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Optional<ServiceResponseDTO>> findService(@PathVariable(name = "name") String name){
        return ResponseEntity.ok(findServiceByName.findService(name));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<ServiceResponseDTO>> findService(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(findServiceById.findById(id));
    }



}
