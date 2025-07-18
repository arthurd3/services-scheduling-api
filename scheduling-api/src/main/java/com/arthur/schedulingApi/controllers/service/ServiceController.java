package com.arthur.schedulingApi.controllers.service;

import com.arthur.schedulingApi.controllers.ApiResponseDTO;
import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.usecases.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    private final CreateService createService;
    private final FindServiceByName findServiceByName;
    private final FindServiceById findServiceById;
    private final DeleteById deleteById;
    private final EditService editService;

    public ServiceController(CreateService createService, FindServiceByName findServiceByName, FindServiceById findServiceById, DeleteById deleteById, EditService editService) {
        this.createService = createService;
        this.findServiceByName = findServiceByName;
        this.findServiceById = findServiceById;
        this.deleteById = deleteById;
        this.editService = editService;
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<ServiceResponseDTO>> createService(@RequestBody @Valid ServiceRequestDTO serviceRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(createService.createService(serviceRequestDTO));
    }

    @GetMapping
    public ResponseEntity<Optional<ServiceResponseDTO>> findByNameService(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(Optional.of(findServiceByName.findService(name)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ServiceResponseDTO>> findByIdService(@PathVariable Long id){
        return ResponseEntity.ok(Optional.of(findServiceById.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> deleteService(@PathVariable Long id){
        deleteById.deleteById(id);
        return ResponseEntity.ok(new ApiResponseDTO("Servico deletado com id: " + id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Optional<ServiceResponseDTO>> editService(@PathVariable Long id, @Valid @RequestBody ServiceRequestDTO serviceRequestDTO){
        return ResponseEntity.ok(Optional.of(editService.editService(id,serviceRequestDTO)));
    }

}
