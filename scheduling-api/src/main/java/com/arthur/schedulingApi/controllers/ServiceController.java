package com.arthur.schedulingApi.controllers;

import com.arthur.schedulingApi.controllers.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;
import com.arthur.schedulingApi.usecases.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final CreateService createService;
    private final FindServiceByName findServiceByName;
    private final FindServiceById findServiceById;
    private final DeleteById deleteById;
    private final EditService editService;


    @ResponseStatus(CREATED)
    @PostMapping
    public ServiceResponseDTO create(@RequestBody @Valid ServiceRequestDTO serviceRequestDTO){
        return createService.createService(serviceRequestDTO);
    }

    @ResponseStatus(FOUND)
    @GetMapping("name/{name}")
    public ServiceResponseDTO findByName(@PathVariable String name){
        return findServiceByName.findService(name);
    }

    @ResponseStatus(FOUND)
    @GetMapping("{id}")
    public ServiceResponseDTO findById(@PathVariable Long id){
        return findServiceById.findById(id);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        deleteById.deleteById(id);
    }

    @ResponseStatus(OK)
    @PutMapping("{id}")
    public ServiceResponseDTO update(@PathVariable Long id,
                                     @Valid @RequestBody ServiceRequestDTO serviceRequestDTO){
        return editService.editService(id,serviceRequestDTO);
    }



}
