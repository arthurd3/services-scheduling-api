package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.UserRoles;
import com.arthur.schedulingApi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static java.lang.Boolean.FALSE;


@Slf4j
@Component
@RequiredArgsConstructor
public class InsertAdminUser {

    @Value("${admin.password}")
    private String password;

    private final UserRepository userRepository;

    public void insertAdminUser() {
        if (FALSE.equals(userRepository.existsUserByRole(UserRoles.ADMIN))){

            log.debug("Administrator user not found, creating...");

            final var user = new User();
            user.setName("Administrator");
            user.setPhoneNumber("12345213556");
            user.setRole(UserRoles.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setEmail("admin@email.com");

            userRepository.save(user);

        } else {
            log.info("insertAdminUser: admin user already exists");
        }
    }
}
