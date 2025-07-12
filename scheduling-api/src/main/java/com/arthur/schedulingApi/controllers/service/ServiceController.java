package com.arthur.schedulingApi.controllers.service;

import com.arthur.schedulingApi.controllers.ApiResponseDTO;
import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.usecases.service.*;
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

    @PostMapping("/create/{ownerId}")
    public ResponseEntity<Optional<ServiceResponseDTO>> createService(@PathVariable(name = "ownerId") Long ownerId , @RequestBody ServiceRequestDTO serviceRequestDTO){
        return ResponseEntity.ok(createService.createService(ownerId,serviceRequestDTO));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Optional<ServiceResponseDTO>> findByNameService(@PathVariable(name = "name") String name){
        return ResponseEntity.ok(findServiceByName.findService(name));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<ServiceResponseDTO>> findByIdService(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(findServiceById.findById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ApiResponseDTO> deleteService(@PathVariable(name = "id") Long id){
        boolean deletedConfirmed = deleteById.deleteById(id);

        if(deletedConfirmed)
            return ResponseEntity.ok(new ApiResponseDTO("Servico deletado com id: " + id));

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO("Servico com id " + id + " não encontrado."));
    }



    @PutMapping("/edit/{id}")
    public ResponseEntity<Optional<ServiceResponseDTO>> editService(@PathVariable Long id, @RequestBody ServiceRequestDTO serviceRequestDTO){
        return ResponseEntity.ok(editService.editService(id,serviceRequestDTO));

    }





}
