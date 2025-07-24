package com.arthur.schedulingApi.controllers;

import com.arthur.schedulingApi.controllers.request.ServiceConfigRequestDTO;
import com.arthur.schedulingApi.usecases.SetServiceConfiguration;
import com.arthur.schedulingApi.utilities.tasks.ScheduleGeneratorTask;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/services/config")
@RequiredArgsConstructor
public class ServiceConfigController {

    private final SetServiceConfiguration setConfig;
    private final ScheduleGeneratorTask generatorTask;

    @ResponseStatus(OK)
    @PostMapping("{id}")
    public void setConfiguration(@PathVariable Long id , @RequestBody ServiceConfigRequestDTO serviceConfig ){
        setConfig.setConfiguration(id, serviceConfig);
    }

    @PostMapping("/trigger-schedule-generation")
    public String triggerScheduleGeneration() {
        generatorTask.forceGenerationForAllActiveConfigs();
        return "Tarefa ... executada manualmente.";
    }
}
