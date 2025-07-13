package com.arthur.schedulingApi.controllers.service.request;

import com.arthur.schedulingApi.models.scheduling.Scheduling;

import java.util.List;

public record ServiceRequestDTO (Long id ,
                                 String name ,
                                 Integer capacity ,
                                 String url_image ,
                                 String description ,
                                 String location ,
                                 List<Scheduling> schedulingList) {


}
