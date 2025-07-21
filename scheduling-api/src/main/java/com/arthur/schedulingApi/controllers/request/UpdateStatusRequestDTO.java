package com.arthur.schedulingApi.controllers.request;

import com.arthur.schedulingApi.models.scheduling.SchedulingStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequestDTO(
        @NotNull(message = "O novo status nao pode ser nulo.")
        SchedulingStatus newStatus
){}
