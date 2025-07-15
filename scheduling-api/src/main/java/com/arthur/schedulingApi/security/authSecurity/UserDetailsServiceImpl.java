package com.arthur.schedulingApi.security.authSecurity;

import com.arthur.schedulingApi.security.userAuth.UserAuthenticated;
import com.arthur.schedulingApi.usecases.user.FindUserByName;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final FindUserByName findUser;

    public UserDetailsServiceImpl(FindUserByName findUser) {
        this.findUser = findUser;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserAuthenticated(findUser.findUserByName(username));
    }



}
