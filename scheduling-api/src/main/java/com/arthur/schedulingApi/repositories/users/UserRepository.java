package com.arthur.schedulingApi.repositories.users;

import com.arthur.schedulingApi.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    Optional<User> findByName(String login);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String s);
}
