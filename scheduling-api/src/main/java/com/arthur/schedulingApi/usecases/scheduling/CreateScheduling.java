package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.controllers.service.request.ServiceRequestDTO;
import com.arthur.schedulingApi.exceptions.ServiceNotFoundException;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.usecases.service.EditService;
import com.arthur.schedulingApi.usecases.service.FindServiceById;
import com.arthur.schedulingApi.usecases.user.FindUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingListToRequest.schedulingListToRequest;
import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToModel.schedulingToModel;
import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToResponse.schedulingToResponse;

@Service
public class CreateScheduling {

    private final FindServiceById findServiceById;
    private final FindUser findUser;
    private final EditService editService;

    public CreateScheduling(FindServiceById findServiceById, FindUser findUser, EditService editService) {
        this.findServiceById = findServiceById;
        this.findUser = findUser;
        this.editService = editService;
    }


    public Optional<SchedulingResponseDTO> createScheduling(SchedulingRequestDTO schedulingRequestDTO) {

        Optional<User> serviceOwner = findUser.findUserEntity(schedulingRequestDTO.ownerId());

        if (serviceOwner.isPresent()) {

            var service = findServiceById.findByIdAsModel(schedulingRequestDTO.serviceId());
            Scheduling scheduling = schedulingToModel(schedulingRequestDTO , service);
            service.addScheduling(scheduling);

            editService.editService(service.getId() , new ServiceRequestDTO(
                    service.getId() ,
                    service.getName() ,
                    service.getCapacity() ,
                    service.getUrl_image(),
                    service.getDescription(),
                    service.getLocation(),
                    schedulingListToRequest(service.getScheduling()))
            );

            return Optional.of(schedulingToResponse(scheduling));
        }

        return Optional.empty();

    }



}
