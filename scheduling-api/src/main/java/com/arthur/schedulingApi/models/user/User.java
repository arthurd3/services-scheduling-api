package com.arthur.schedulingApi.models.user;

import com.arthur.schedulingApi.models.scheduling.Scheduling;
import com.arthur.schedulingApi.models.service.Service;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
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
    @Column(name="phone_number" , unique = true, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRoles role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> servicesOwned = new ArrayList<>();


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Scheduling> schedulings = new ArrayList<>();

    public User(Long id, String name, String email, String password, String phoneNumber, UserRoles role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (role) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_" + UserRoles.ADMIN.getRole()),
                    new SimpleGrantedAuthority("ROLE_" + UserRoles.MANAGER.getRole()),
                    new SimpleGrantedAuthority("ROLE_" + UserRoles.MANAGER_VIP.getRole()),
                    new SimpleGrantedAuthority("ROLE_" + UserRoles.USER.getRole())
            );
            case MANAGER_VIP -> List.of(
                    new SimpleGrantedAuthority("ROLE_" + UserRoles.MANAGER.getRole()),
                    new SimpleGrantedAuthority("ROLE_" + UserRoles.MANAGER_VIP.getRole()),
                    new SimpleGrantedAuthority("ROLE_" + UserRoles.USER.getRole())
            );
            case MANAGER -> List.of(
                    new SimpleGrantedAuthority("ROLE_" + UserRoles.MANAGER.getRole())
            );
            default -> List.of(
                    new SimpleGrantedAuthority("ROLE_" + UserRoles.USER.getRole())
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

    public List<Scheduling> getSchedulings() {
        return this.schedulings;
    }

    public List<Service> getServicesOwned() {
        return this.servicesOwned;
    }

    public void setEncodePassword(String rawPassword, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(rawPassword);
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
