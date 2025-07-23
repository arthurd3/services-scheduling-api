package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.User;
import org.springframework.stereotype.Component;

@Component
public class VerifyScheduling extends Verify {

    public void verifyEdit(User userOwner , Scheduling schedulingEdit) {
        if(super.verifyEdit(userOwner))
            return;

        verifyUserIsOwner(userOwner.getId() , schedulingEdit.getServices().getOwner().getId());
    }

    public void verifyDelete(User ownerDelete , Long schedulingId) {
        super.verifyEdit(ownerDelete);

        verifyUserIsOwner(ownerDelete.getId() , schedulingId);
    }
}