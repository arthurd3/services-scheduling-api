package com.arthur.schedulingApi.controllers.service.request;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingSlotRequestDTO;

import java.util.List;

public record ServiceRequestDTO (Long id ,
                                 String name ,
                                 Integer capacity ,
                                 String url_image ,
                                 String description ,
                                 String location ,
                                 List<SchedulingSlotRequestDTO> schedulingList) {


}
