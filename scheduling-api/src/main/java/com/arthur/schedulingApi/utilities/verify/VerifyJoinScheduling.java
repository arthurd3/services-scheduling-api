package com.arthur.schedulingApi.utilities.verify;

import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VerifyJoinScheduling extends Verify{

    public void verifyJoin(User client , Scheduling scheduling){
        verifySchedulingIsAvailable(scheduling);
        verifyClientIsNotServiceOwner(client , scheduling);
    }

}
