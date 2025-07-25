package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.ServiceConfigRequestDTO;
import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.repositories.ServiceConfigurationRepository;
import com.arthur.schedulingApi.repositories.ServiceRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.utilities.verify.VerifyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import static com.arthur.schedulingApi.usecases.mapper.ServiceConfigToModel.convertConfigToModel;
import static com.arthur.schedulingApi.usecases.mapper.ServiceConfigToModel.updateModel;

@Service
@RequiredArgsConstructor
public class SetServiceConfiguration {

    private final FindService findService;
    private final VerifyService verifyService;
    private final AuthenticatedUserService  authenticatedUserService;
    private final ServiceRepository serviceRepository;

    @Transactional
    public void setConfiguration(Long serviceId, ServiceConfigRequestDTO serviceConfigDTO) {
        Services serviceFounded = findService.findByIdAsModel(serviceId);

        var userEdit = authenticatedUserService.getAuthenticatedUser();

        verifyService.verifyEdit(userEdit, serviceFounded);

        if(serviceFounded.getConfiguration() == null){
            serviceFounded.setConfiguration(convertConfigToModel(serviceConfigDTO));
        }
        else{
            var configEntity = serviceFounded.getConfiguration();
            serviceFounded.setConfiguration(updateModel(configEntity ,serviceConfigDTO));
        }

        serviceRepository.save(serviceFounded);
    }
}
