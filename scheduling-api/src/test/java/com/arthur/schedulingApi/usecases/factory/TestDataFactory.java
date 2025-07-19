package com.arthur.schedulingApi.usecases.factory;

import com.arthur.schedulingApi.models.service.Services;
import com.arthur.schedulingApi.models.user.User;
import com.arthur.schedulingApi.models.user.UserRoles;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DisplayName("Factory from create Objects for Test")
public class TestDataFactory {

    @DisplayName("Create a User for test")
    public static User createTestUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Arthur - O Profissional");
        user.setEmail("profissional@email.com");
        user.setRole(UserRoles.MANAGER);
        return user;
    }

    @DisplayName("Create a Service for test")
    public static Services createTestService() {
        Services service = new Services();
        service.setId(101L);
        service.setName("Consulta de Nutrição");
        service.setCapacity(1);
        service.setDescription("Avaliação completa e plano alimentar personalizado.");
        service.setLocation("Atendimento Online via Google Meet");
        service.setUrl_image("https://example.com/images/nutricao.png");
        service.setOwner(createTestUser());
        service.setScheduling(new ArrayList<>());
        service.setCreatedAt(LocalDateTime.now());
        return service;
    }


    @DisplayName("Create a List<User> with size 6 for test")
    public static List<User> createdListOfUsers()
    {
        List<User> userList = List.of(
                new User("Carlos Silva", "carlos.silva@email.com", "senhaHash1", "32991234567"),
                new User("Beatriz Costa", "beatriz.costa@email.com", "senhaHash2", "21987654321"),
                new User("Fernanda Lima", "fernanda.lima@email.com", "senhaHash3", "11988887777"),
                new User("Ricardo Souza", "ricardo.souza@email.com", "senhaHash4", "41977778888"),
                new User("Juliana Alves", "juliana.alves@email.com", "senhaHash5", "85966665555"),
                new User("Admin Principal", "admin@schedulingapi.com", "senhaHashAdmin", "99999999999")
        );

        userList.get(0).setRole(UserRoles.USER);
        userList.get(1).setRole(UserRoles.USER);
        userList.get(2).setRole(UserRoles.USER);
        userList.get(3).setRole(UserRoles.USER);
        userList.get(4).setRole(UserRoles.USER);
        userList.get(5).setRole(UserRoles.USER);

        return userList;
    }

}