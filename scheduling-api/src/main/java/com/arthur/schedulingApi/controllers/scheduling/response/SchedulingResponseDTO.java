package com.arthur.schedulingApi.controllers.scheduling.response;

import com.arthur.schedulingApi.models.scheduling.SchedulingStatus;

import java.time.LocalDateTime;

public record SchedulingResponseDTO(Long id ,
                                    SchedulingStatus status ,
                                    LocalDateTime startTime ,
                                    LocalDateTime endTime) {
}
