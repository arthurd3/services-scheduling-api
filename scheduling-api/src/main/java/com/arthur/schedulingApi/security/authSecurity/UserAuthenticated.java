package com.arthur.schedulingApi.security.authSecurity;

import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.arthur.schedulingApi.models.enums.UserRoles.*;

public class UserAuthenticated implements UserDetails {

   private final User user;

    public UserAuthenticated(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (user.getRole()) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_" + ADMIN.getRole()),
                    new SimpleGrantedAuthority("ROLE_" + MANAGER.getRole()),
                    new SimpleGrantedAuthority("ROLE_" + MANAGER_VIP.getRole()),
                    new SimpleGrantedAuthority("ROLE_" + USER.getRole())
            );
            case MANAGER_VIP -> List.of(
                    new SimpleGrantedAuthority("ROLE_" + MANAGER.getRole()),
                    new SimpleGrantedAuthority("ROLE_" + MANAGER_VIP.getRole()),
                    new SimpleGrantedAuthority("ROLE_" + USER.getRole())
            );
            case MANAGER -> List.of(
                    new SimpleGrantedAuthority("ROLE_" + MANAGER.getRole())
            );
            default -> List.of(
                    new SimpleGrantedAuthority("ROLE_" + USER.getRole())
            );
        };
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public User getUser() {
        return this.user;
    }
}
