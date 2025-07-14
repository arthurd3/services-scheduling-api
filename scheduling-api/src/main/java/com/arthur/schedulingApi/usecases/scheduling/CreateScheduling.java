package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.controllers.scheduling.request.SchedulingRequestDTO;
import com.arthur.schedulingApi.controllers.scheduling.response.SchedulingResponseDTO;
import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.service.Services;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.repositories.scheduling.SchedulingRepository;
import com.arthur.schedulingApi.usecases.service.EditService;
import com.arthur.schedulingApi.usecases.service.FindServiceById;
import com.arthur.schedulingApi.usecases.user.FindUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToModel.schedulingToModel;
import static com.arthur.schedulingApi.usecases.scheduling.mapper.SchedulingToResponse.schedulingToResponse;
import static com.arthur.schedulingApi.usecases.service.mapper.ServiceToRequest.serviceToRequest;

@Service
public class CreateScheduling {

    private final FindServiceById findServiceById;
    private final FindUser findUser;
    private final EditService editService;
    private final SchedulingRepository schedulingRepository;

    public CreateScheduling(FindServiceById findServiceById, FindUser findUser, EditService editService, SchedulingRepository schedulingRepository) {
        this.findServiceById = findServiceById;
        this.findUser = findUser;
        this.editService = editService;
        this.schedulingRepository = schedulingRepository;
    }

    @Transactional
    public Optional<SchedulingResponseDTO> createScheduling(SchedulingRequestDTO schedulingRequestDTO) {

        Optional<User> serviceOwner = findUser.findUserEntity(schedulingRequestDTO.ownerId());
        Services service = findServiceById.findByIdAsModel(schedulingRequestDTO.serviceId());

        if (serviceOwner.isPresent() && service != null) {

            Scheduling scheduling = schedulingToModel(schedulingRequestDTO , service);

            var schedulingReturn = schedulingRepository.save(scheduling);

            service.addScheduling(scheduling);

            return Optional.of(schedulingToResponse(schedulingReturn));
        }

        return Optional.empty();

    }



}
