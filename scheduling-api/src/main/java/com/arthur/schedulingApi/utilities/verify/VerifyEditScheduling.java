package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.User;
import org.springframework.stereotype.Component;

@Component
public class VerifyEditScheduling extends VerifyEdit {

    public void verifyEdit(User ownerEdit , Scheduling schedulingEdit) {
        super.verifyEdit(ownerEdit);

        verifyUserIsOwner(ownerEdit.getId() , schedulingEdit.getServices().getOwner().getId());
    }
}