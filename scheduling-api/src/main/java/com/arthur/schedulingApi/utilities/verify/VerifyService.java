package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.models.User;
import org.springframework.stereotype.Component;

@Component
public class VerifyService extends Verify {

    public void verifyEdit(User userOwner , Services serviceEdit) {
        if(super.verifyEdit(userOwner))
            return;

        verifyUserIsOwner(userOwner.getId(),  serviceEdit.getOwner().getId());
    }

    public void verifyDelete(User ownerDelete , Services serviceDelete) {
        super.verifyEdit(ownerDelete);

        verifyUserIsOwner(ownerDelete.getId(),  serviceDelete.getOwner().getId());
    }
}