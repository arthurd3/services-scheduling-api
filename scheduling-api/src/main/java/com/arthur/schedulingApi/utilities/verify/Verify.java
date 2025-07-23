package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.exceptions.InvalidRatingException;
import com.arthur.schedulingApi.exceptions.SchedulingNotAvailableException;
import com.arthur.schedulingApi.exceptions.UnauthorizedException;
import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.models.enums.UserRoles;
import com.arthur.schedulingApi.models.ratingImpl.ServiceRating;

public abstract class Verify {

     protected boolean verifyEdit(User userOwner) {
        return verifyUserIsAdmin(userOwner);
     }

    protected void verifyUserIsSame(Long ownerId , Long userEditId) {
        if(!ownerId.equals(userEditId)){
            throw new UnauthorizedException("Voce nao pode editar um Usuario que nao seja voce!");
        }
    }

    protected void verifyUserIsOwner(Long ownerId , Long ownerServiceId) {

        if(!ownerId.equals(ownerServiceId))
            throw new UnauthorizedException("Voce nao e o dono desse Servico para efetuar essa acao");
    }

    protected boolean verifyUserIsAdmin(User user) {
        return user.getRole() == UserRoles.ADMIN;
    }


    protected void verifyClientIsNotServiceOwner(User client , Scheduling scheduling){
        Long serviceId = scheduling.getServices().getId();

        boolean isOwner = client.getServicesOwned().stream()
                .anyMatch(service -> service.getId().equals(serviceId));

        if (isOwner) {
            throw new UnauthorizedException("Você não pode agendar no seu próprio serviço.");
        }
    }

    protected void verifySchedulingIsAvailable(Scheduling scheduling) {
        if (scheduling.getStatus() != SchedulingStatus.AVAILABLE) {
            throw new SchedulingNotAvailableException("Este horário não está mais disponível.");
        }
    }


    protected void verifyUserNotOwner(User userAppraiser, ServiceRating rating) {
        var serviceRating = rating.getServiceRatee();
        boolean isOwner = serviceRating.getOwner().getId().equals(userAppraiser.getId());

        if (isOwner) {
            throw new InvalidRatingException("Não é possível avaliar o próprio serviço");
        }
    }

    protected void verifyRateeIsNotAppraiser(User userAppraiser, Long userRatingId) {

        if (userAppraiser.getId().equals(userRatingId)) {
            throw new InvalidRatingException("Não é possível avaliar o próprio usuário");
        }
    }
}