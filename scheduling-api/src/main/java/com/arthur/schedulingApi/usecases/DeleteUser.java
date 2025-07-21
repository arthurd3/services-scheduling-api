package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUser {


    private final UserRepository userRepository;

    public DeleteUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUser(Long userId) {

        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException("Nao foi possivel deletar o Usuario com id " + userId);
        }

        userRepository.deleteById(userId);
    }

}
