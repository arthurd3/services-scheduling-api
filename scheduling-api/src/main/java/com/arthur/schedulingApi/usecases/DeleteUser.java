package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.exceptions.UserNotFoundException;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.repositories.UserRepository;
import com.arthur.schedulingApi.security.jwt.AuthenticatedUserService;
import com.arthur.schedulingApi.utilities.verify.VerifyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUser {

    private final UserRepository userRepository;
    private final AuthenticatedUserService  authenticatedUserService;
    private final VerifyUser verify;

    @CacheEvict(value = "USER_CACHE", key = "#userId")
    public void deleteUser(Long userId) {

        User authUser = authenticatedUserService.getAuthenticatedUser();

        verify.verifyDelete(authUser , userId);

        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException("Nao foi possivel deletar o Usuario com id " + userId);
        }

        userRepository.deleteById(userId);
    }
}