package com.arthur.schedulingApi.controllers.scheduling.request;

import com.arthur.schedulingApi.models.scheduling.SchedulingStatus;

import java.time.LocalDateTime;

public record SchedulingRequestDTO (Long id,
                                    Long ownerId ,
                                    Long serviceId ,
                                    SchedulingStatus status ,
                                    LocalDateTime endTime ,
                                    LocalDateTime startTime ) {
}
