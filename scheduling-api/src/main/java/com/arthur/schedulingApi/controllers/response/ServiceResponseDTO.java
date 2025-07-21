package com.arthur.schedulingApi.controllers.response;


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
                                  LocalDateTime createdAt,
                                  Double score,
                                  Integer ratingAmount
) {}
