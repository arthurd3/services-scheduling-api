package com.arthur.schedulingApi.usecases.user;

import com.arthur.schedulingApi.repositories.users.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUser {


    private final UserRepository userRepository;

    public DeleteUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean deleteUser(Long userId) {

        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return true;
        }

        return false;

    }

}
