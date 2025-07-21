package com.arthur.schedulingApi.controllers.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingSlotRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.request.UpdateStatusRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.usecases.scheduling.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/scheduling")
@RequiredArgsConstructor
public class SchedulingController {

    private final CreateScheduling createScheduling;
    private final FindScheduling findScheduling;
    private final FindUserScheduling findUserScheduling;
    private final DeleteScheduling deleteScheduling;
    private final ChangeStatusScheduling changeStatusScheduling;
    private final JoinScheduling joinScheduling;
    private final EditScheduling editScheduling;

    @ResponseStatus(CREATED)
    @PostMapping("{id}")
    public SchedulingResponseDTO create(@PathVariable(name = "id") Long serviceId ,
                                                  @RequestBody @Valid SchedulingSlotRequestDTO schedulingSlotRequestDTO) {
        return createScheduling.createScheduling(schedulingSlotRequestDTO, serviceId);
    }

    @ResponseStatus(OK)
    @GetMapping("{id}")
    public SchedulingResponseDTO findById(@PathVariable Long id) {
        return findScheduling.findScheduling(id);
    }

    @ResponseStatus(OK)
    @PatchMapping("updateTime/{id}")
    public SchedulingResponseDTO updateTime(@PathVariable Long id ,
                                            @RequestBody SchedulingSlotRequestDTO schedulingSlotRequestDTO) {
        return editScheduling.editSchedulingTime(id , schedulingSlotRequestDTO);
    }

    @ResponseStatus(OK)
    @GetMapping("myScheduling")
    public Page<SchedulingResponseDTO> findUserScheduling(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "5") int size) {
        return findUserScheduling.findUserScheduling(PageRequest.of(page , size));
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        deleteScheduling.deleteScheduling(id);
    }

    @PatchMapping("updateStatus/{id}")
    public SchedulingResponseDTO updateStatus(@PathVariable Long id,
                                              @RequestBody UpdateStatusRequestDTO statusDTO) {
        return changeStatusScheduling.changeStatus(statusDTO , id);
    }

    @ResponseStatus(OK)
    @PostMapping("join/{id}")
    public SchedulingResponseDTO join(@PathVariable Long id) {
        return joinScheduling.joinScheduling(id);
    }
}
