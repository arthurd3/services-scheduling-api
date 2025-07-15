package com.arthur.schedulingApi.controllers.user.response;

import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;

import java.util.List;

public record UserResponseDTO (Long id ,
                               String name,
                               String email,
                               String phoneNumber,
                               String role,
                               List<SchedulingResponseDTO> schedulingList,
                               List<ServiceResponseDTO> serviceList){
}
