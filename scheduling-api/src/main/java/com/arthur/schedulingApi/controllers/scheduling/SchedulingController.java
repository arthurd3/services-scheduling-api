package com.arthur.schedulingApi.controllers.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.usecases.scheduling.CreateScheduling;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/scheduling")
public class SchedulingController {

    private final CreateScheduling createScheduling;

    public SchedulingController(CreateScheduling createScheduling) {
        this.createScheduling = createScheduling;
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<SchedulingResponseDTO>> createScheduling(@RequestBody SchedulingRequestDTO schedulingRequestDTO) {
        return ResponseEntity.ok(createScheduling.createScheduling(schedulingRequestDTO));
    }
}
