package com.arthur.schedulingApi.controllers.response;

import java.util.List;

public record UserResponseDTO (Long id ,
                               String name,
                               String email,
                               String phoneNumber,
                               String role,
                               List<SchedulingResponseDTO> schedulingList,
                               List<ServiceResponseDTO> serviceList,
                               Double score,
                               Integer ratingAmount
) {}
