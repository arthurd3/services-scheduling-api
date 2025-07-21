package com.arthur.schedulingApi.controllers.scheduling;

import com.arthur.schedulingApi.controllers.ApiResponseDTO;
import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.request.UpdateStatusRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.usecases.scheduling.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
    private final EditScheduling editScheduling;

    public SchedulingController(CreateScheduling createScheduling, FindScheduling findScheduling, FindUserScheduling findUserScheduling, DeleteScheduling deleteScheduling, ChangeStatusScheduling changeStatusScheduling, JoinScheduling joinScheduling, EditScheduling editScheduling) {
        this.createScheduling = createScheduling;
        this.findScheduling = findScheduling;
        this.findUserScheduling = findUserScheduling;
        this.deleteScheduling = deleteScheduling;
        this.changeStatusScheduling = changeStatusScheduling;
        this.joinScheduling = joinScheduling;
        this.editScheduling = editScheduling;
    }

    @PostMapping("/{serviceId}/create")
    public ResponseEntity<SchedulingResponseDTO> createScheduling(@PathVariable Long serviceId ,
                                                                  @RequestBody @Valid SchedulingSlotRequestDTO schedulingSlotRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createScheduling.createScheduling(schedulingSlotRequestDTO, serviceId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponseDTO> findSchedulingById(@PathVariable Long id) {
        return ResponseEntity.ok(findScheduling.findScheduling(id));
    }

    @PatchMapping("/{id}/time")
    public ResponseEntity<SchedulingResponseDTO> editSchedulingTimeById(@PathVariable Long id ,
                                                                                  @RequestBody SchedulingSlotRequestDTO schedulingSlotRequestDTO) {
        return ResponseEntity.ok(editScheduling.editSchedulingTime(id , schedulingSlotRequestDTO));
    }

    @GetMapping("/userScheduling")
    public ResponseEntity<Page<SchedulingResponseDTO>> findUserScheduling(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                          @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(findUserScheduling.findUserScheduling(PageRequest.of(page , size)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> deleteScheduling(@PathVariable Long id) {
        deleteScheduling.deleteScheduling(id);
        return ResponseEntity.ok(new ApiResponseDTO("Agendamento deletado com sucesso!"));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<SchedulingResponseDTO> changeStatus(@PathVariable Long id, @RequestBody UpdateStatusRequestDTO statusDTO) {
        return ResponseEntity.ok(changeStatusScheduling.changeStatus(statusDTO , id));
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<SchedulingResponseDTO> joinScheduling(@PathVariable Long id) {
        return ResponseEntity.ok(joinScheduling.joinScheduling(id));
    }
}
