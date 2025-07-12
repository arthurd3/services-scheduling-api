package com.arthur.schedulingApi.controllers.user.response;

import com.arthur.schedulingApi.controllers.service.response.ServiceResponseDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.user.UserRoles;

import java.util.List;

public record UserResponseDTO (Long id ,
                               String name,
                               String email,
                               String phoneNumber,
                               String role,
                               List<Scheduling> schedulingList,
                               List<ServiceResponseDTO> serviceList){
}
