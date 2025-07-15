package com.arthur.schedulingApi.controllers.scheduling.request;

import com.arthur.schedulingApi.models.scheduling.SchedulingStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequestDTO(@NotNull SchedulingStatus newStatus) {
}
