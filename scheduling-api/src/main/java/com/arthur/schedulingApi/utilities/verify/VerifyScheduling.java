package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.User;
import org.springframework.stereotype.Component;

@Component
public class VerifyScheduling extends Verify {

    public void verifyEdit(User ownerEdit , Scheduling schedulingEdit) {
        super.verifyEdit(ownerEdit);

        verifyUserIsOwner(ownerEdit.getId() , schedulingEdit.getServices().getOwner().getId());
    }

    public void verifyDelete(User ownerDelete , Long schedulingId) {
        super.verifyEdit(ownerDelete);

        verifyUserIsOwner(ownerDelete.getId() , schedulingId);
    }
}