package com.arthur.schedulingApi.controllers.service.response;

import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.controllers.user.resume.UserResumeDTO;


import java.time.LocalDateTime;
import java.util.List;

public record ServiceResponseDTO (Long id,
                                  String name,
                                  UserResumeDTO ownerUser,
                                  Integer capacity,
                                  String description,
                                  String location,
                                  String url_image,
                                  List<SchedulingResponseDTO>scheduling,
                                  LocalDateTime createdAt) {
}
