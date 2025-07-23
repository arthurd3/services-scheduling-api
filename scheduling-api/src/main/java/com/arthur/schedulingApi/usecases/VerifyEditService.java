package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.UnauthorizedException;
import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.UserRoles;
import org.springframework.stereotype.Component;

@Component
public class VerifyEditService {

    public void verifyService(User owner , Services service) {
        if(verifyUserIsAdmin(owner))
            return;

        verifyUserIsOwner(owner.getId() , service.getOwner().getId());
    }

    private void verifyUserIsOwner(Long ownerId , Long ownerServiceId) {
        
        if(!ownerId.equals(ownerServiceId))
            throw new UnauthorizedException("Voce nao e o dono desse Servico para editar");
    }

    private boolean verifyUserIsAdmin(User user) {
        return user.getRole() == UserRoles.ADMIN;
    }
}