package com.arthur.schedulingApi.controllers.service.request;


public record ServiceRequestDTO (Long id ,
                                 String name ,
                                 Integer capacity ,
                                 String url_image ,
                                 String description ,
                                 String location ) {


}
