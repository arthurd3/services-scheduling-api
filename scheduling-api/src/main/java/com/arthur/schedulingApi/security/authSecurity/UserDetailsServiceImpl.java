package com.arthur.schedulingApi.security.authSecurity;

import com.arthur.schedulingApi.usecases.FindUserByName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final FindUserByName findUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new UserAuthenticated(findUser.findUserByName(username));
    }
}