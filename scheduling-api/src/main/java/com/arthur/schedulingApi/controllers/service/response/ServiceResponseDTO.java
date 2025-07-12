package com.arthur.schedulingApi.controllers.service.response;

import com.arthur.schedulingApi.models.service.Scheduling;

import java.time.LocalDateTime;
import java.util.List;

public record ServiceResponseDTO (String name,
                                  Long ownerId,
                                  Integer capacity,
                                  String description,
                                  String location,
                                  String url_image,
                                  List<Scheduling>scheduling,
                                  LocalDateTime createdAt) {
}
