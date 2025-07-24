package com.arthur.schedulingApi.repositories;

import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.UserRoles;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Pageable pageable);

    Optional<User> findByName(String name);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String s);

    boolean existsByName(String name);

    boolean existsUserByRole(UserRoles role);

}
