package com.arthur.schedulingApi.controllers.service.response;

import com.arthur.schedulingApi.controllers.user.response.UserResumeDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;


import java.time.LocalDateTime;
import java.util.List;

public record ServiceResponseDTO (String name,
                                  UserResumeDTO ownerUser,
                                  Integer capacity,
                                  String description,
                                  String location,
                                  String url_image,
                                  List<Scheduling>scheduling,
                                  LocalDateTime createdAt) {
}
