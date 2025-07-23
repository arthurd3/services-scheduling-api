package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.exceptions.UnauthorizedException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.UserRoles;

public abstract class VerifyEdit {

     protected void verifyEdit(User userOwner) {
        if(verifyUserIsAdmin(userOwner))
            return;
     }

    protected void verifyUserIsSame(Long ownerId , Long userEditId) {
        if(!ownerId.equals(userEditId)){
            throw new UnauthorizedException("Voce nao pode editar um Usuario que nao seja voce!");
        }
    }

    protected void verifyUserIsOwner(Long ownerId , Long ownerServiceId) {

        if(!ownerId.equals(ownerServiceId))
            throw new UnauthorizedException("Voce nao e o dono desse Servico para editar");
    }

    protected boolean verifyUserIsAdmin(User user) {
        return user.getRole() == UserRoles.ADMIN;
    }
}