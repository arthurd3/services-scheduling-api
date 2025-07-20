package com.arthur.schedulingApi.usecases.scheduling;

import com.arthur.schedulingApi.repositories.scheduling.SchedulingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FindUserSchedulingTest {

    @InjectMocks
    private FindUserScheduling joinScheduling;

    @Mock
    private SchedulingRepository schedulingRepository;


    @Test
    void findUserScheduling() {
    }
}