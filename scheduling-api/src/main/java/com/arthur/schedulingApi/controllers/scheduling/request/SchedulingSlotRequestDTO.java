package com.arthur.schedulingApi.controllers.scheduling.request;

import java.time.LocalDateTime;

public record SchedulingSlotRequestDTO(LocalDateTime endTime ,
                                       LocalDateTime startTime ) {
}
