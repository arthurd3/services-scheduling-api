package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.controllers.request.ServiceRequestDTO;
import com.arthur.schedulingApi.controllers.response.ServiceResponseDTO;

import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import static com.arthur.schedulingApi.utilities.copyproperties.ServiceCopyProperties.copyServiceProperties;
import static com.arthur.schedulingApi.usecases.mapper.ServiceToResponse.serviceToResponse;

@Service
@RequiredArgsConstructor
public class EditService {

    private final FindService findService;
    private final AuthenticatedUserService  authenticatedUserService;
    private final VerifyEditService verifyEditService;

    @Transactional
    @CachePut(value = "SERVICE_CACHE" , key = "#result.id()")
    public ServiceResponseDTO editService(Long id ,ServiceRequestDTO serviceRequestDTO) {

        User authenticatedUser = authenticatedUserService.getAuthenticatedUser();

        var originalService = findService.findByIdAsModel(id);

        verifyEditService.verifyService(authenticatedUser, originalService);

        var editedService = copyServiceProperties(serviceRequestDTO , originalService);

        return serviceToResponse(editedService);
    }
}