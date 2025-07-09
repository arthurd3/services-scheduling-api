package com.arthur.schedulingApi.models.user;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String phoneNumber;

    private UserRoles role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (role) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_MANAGER_VIP"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
            case VIP -> List.of(
                    new SimpleGrantedAuthority("ROLE_MANAGER"),
                    new SimpleGrantedAuthority("ROLE_MANAGER_VIP"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
            case MANAGER -> List.of(
                    new SimpleGrantedAuthority("ROLE_MANAGER")
            );
            default -> List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        };
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public UserRoles getRole() {
        return this.role;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Long getId() {
        return this.id;
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
}
