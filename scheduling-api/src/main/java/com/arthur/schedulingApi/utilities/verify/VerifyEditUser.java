package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.models.User;
import org.springframework.stereotype.Component;

@Component
public class VerifyEditUser extends VerifyEdit {

    public void verifyEdit(User userOwner , User userEdit) {
        super.verifyEdit(userOwner);

        verifyUserIsSame(userOwner.getId(), userEdit.getId());
    }
}
