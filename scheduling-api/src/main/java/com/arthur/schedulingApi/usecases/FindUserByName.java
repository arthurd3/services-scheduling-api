package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FindUserByName {

    private final UserRepository userRepository;

    public FindUserByName(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("Usuario com nome "+ name +" nao encontrado!"));
    }


}
