package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.models.User;
import org.springframework.stereotype.Component;

@Component
public class VerifyUser extends Verify {

    public void verifyEdit(User userOwner , User userEdit) {
        super.verifyEdit(userOwner);

        verifyUserIsSame(userOwner.getId(), userEdit.getId());
    }

    public void verifyDelete(User ownerDelete , Long userIdDelete) {
        super.verifyEdit(ownerDelete);

        verifyUserIsSame(ownerDelete.getId(), userIdDelete);
    }
}
