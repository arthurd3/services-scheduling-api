package com.arthur.schedulingApi.repositories.users;

import com.arthur.schedulingApi.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    UserDetails findByName(String name);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String s);
}
