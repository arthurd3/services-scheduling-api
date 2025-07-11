package com.arthur.schedulingApi.controllers.service;

import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    @PostMapping("/create/{id}")
    public ResponseEntity<Optional<ServiceResponseDTO>> createService(@PathVariable String ownerId , @RequestBody ServiceRequestDTO serviceRequestDTO){
        return ResponseEntity.ok(Optional.of(null));
    }


}
