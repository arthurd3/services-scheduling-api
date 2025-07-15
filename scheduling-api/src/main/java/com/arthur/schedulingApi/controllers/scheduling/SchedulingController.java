package com.arthur.schedulingApi.controllers.scheduling;

import com.arthur.schedulingApi.controllers.ApiResponseDTO;
import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.scheduling.SchedulingStatus;
import com.arthur.schedulingApi.usecases.scheduling.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/scheduling")
public class SchedulingController {

    private final CreateScheduling createScheduling;
    private final FindScheduling findScheduling;
    private final FindUserScheduling findUserScheduling;
    private final DeleteScheduling deleteScheduling;
    private final ChangeStatusScheduling changeStatusScheduling;
    private final JoinScheduling joinScheduling;

    public SchedulingController(CreateScheduling createScheduling, FindScheduling findScheduling, FindUserScheduling findUserScheduling, DeleteScheduling deleteScheduling, ChangeStatusScheduling changeStatusScheduling, JoinScheduling joinScheduling) {
        this.createScheduling = createScheduling;
        this.findScheduling = findScheduling;
        this.findUserScheduling = findUserScheduling;
        this.deleteScheduling = deleteScheduling;
        this.changeStatusScheduling = changeStatusScheduling;
        this.joinScheduling = joinScheduling;
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<SchedulingResponseDTO>> createScheduling(@RequestBody SchedulingRequestDTO schedulingRequestDTO) {
        return ResponseEntity.ok(createScheduling.createScheduling(schedulingRequestDTO));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<SchedulingResponseDTO>> findSchedulingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Optional.of(findScheduling.findScheduling(id)));
    }

    @GetMapping("/findUserScheduling/{id}")
    public ResponseEntity<List<SchedulingResponseDTO>> findUserScheduling(@PathVariable(name = "id") Long userId ,
                                                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                                                           @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(findUserScheduling.findUserScheduling(userId , PageRequest.of(page , size)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDTO> deleteScheduling(@PathVariable(name = "id") Long idScheduling) {
        deleteScheduling.deleteScheduling(idScheduling);
        return ResponseEntity.ok(new ApiResponseDTO("Agendamento deletado com sucesso!"));
    }

    @PostMapping("/changeStatus/{id}")
    public ResponseEntity<SchedulingResponseDTO> changeStatus(@PathVariable(name = "id") Long idScheduling , @RequestBody SchedulingStatus status) {
        return ResponseEntity.ok(changeStatusScheduling.changeStatus(status , idScheduling));
    }

    @PostMapping("/joinScheduling/{id}")
    public ResponseEntity<SchedulingResponseDTO> joinScheduling(@PathVariable(name = "id") Long userId ,@RequestBody SchedulingRequestDTO schedulingRequestDTO) {
        return ResponseEntity.ok(joinScheduling.joinScheduling(userId , schedulingRequestDTO));
    }
}
