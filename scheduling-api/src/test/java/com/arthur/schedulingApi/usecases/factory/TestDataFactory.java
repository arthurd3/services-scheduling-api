package com.arthur.schedulingApi.usecases.factory;

import com.arthur.schedulingApi.models.Scheduling;
import com.arthur.schedulingApi.models.enums.SchedulingStatus;
import com.arthur.schedulingApi.models.Services;
import com.arthur.schedulingApi.models.User;
import com.arthur.schedulingApi.models.enums.UserRoles;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@DisplayName("Factory from create Objects for Test")
public class TestDataFactory {

    private static final Faker FAKER = new Faker(new Locale("pt-BR"));

    @DisplayName("Create a random User for test")
    public static User createRandomUserTest() {
        User user = new User();
        user.setId(FAKER.number().randomNumber());
        user.setName(FAKER.name().fullName());
        user.setEmail(FAKER.internet().emailAddress());
        user.setPhoneNumber(FAKER.phoneNumber().cellPhone().replaceAll("[^0-9]", ""));
        user.setRole(UserRoles.USER);
        return user;
    }

    @DisplayName("Create a User for test")
    public static User createTestUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Arthur - O Profissional");
        user.setEmail("profissional@email.com");
        user.setRole(UserRoles.MANAGER);
        user.setPhoneNumber("3123123412412412");
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

    @DisplayName("Create a Scheduling for Test")
    public static Scheduling createTestScheduling(Services service) {

        Scheduling scheduling = new Scheduling();

        scheduling.setId(201L);
        scheduling.setStartTime(LocalDateTime.now().plusDays(1).withHour(14).withMinute(0));
        scheduling.setEndTime(LocalDateTime.now().plusDays(1).withHour(15).withMinute(0));
        scheduling.setStatus(SchedulingStatus.AVAILABLE);
        scheduling.setServices(service);
        scheduling.setClient(null);

        return scheduling;
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

    @DisplayName("Create a List of Schedulings for test")
    public static List<Scheduling> createListOfSchedulings() {

        Services parentService = createTestService();
        User client1 = createRandomUserTest();
        User client2 = createRandomUserTest();


        Scheduling scheduling1_booked = new Scheduling();
        scheduling1_booked.setId(201L);
        scheduling1_booked.setServices(parentService);
        scheduling1_booked.setStartTime(LocalDateTime.now().plusDays(2).withHour(9).withMinute(0).withNano(0));
        scheduling1_booked.setEndTime(LocalDateTime.now().plusDays(2).withHour(10).withMinute(0).withNano(0));
        scheduling1_booked.setStatus(SchedulingStatus.BOOKED);
        scheduling1_booked.setClient(client1);

        Scheduling scheduling2_booked = new Scheduling();
        scheduling2_booked.setId(202L);
        scheduling2_booked.setServices(parentService);
        scheduling2_booked.setStartTime(LocalDateTime.now().plusDays(2).withHour(10).withMinute(0).withNano(0));
        scheduling2_booked.setEndTime(LocalDateTime.now().plusDays(2).withHour(11).withMinute(0).withNano(0));
        scheduling2_booked.setStatus(SchedulingStatus.BOOKED);
        scheduling2_booked.setClient(client2);

        Scheduling scheduling3_available = new Scheduling();
        scheduling3_available.setId(203L);
        scheduling3_available.setServices(parentService);
        scheduling3_available.setStartTime(LocalDateTime.now().plusDays(2).withHour(11).withMinute(0).withNano(0));
        scheduling3_available.setEndTime(LocalDateTime.now().plusDays(2).withHour(12).withMinute(0).withNano(0));
        scheduling3_available.setStatus(SchedulingStatus.AVAILABLE);
        scheduling3_available.setClient(null);

        Scheduling scheduling4_past = new Scheduling();
        scheduling4_past.setId(204L);
        scheduling4_past.setServices(parentService);
        scheduling4_past.setStartTime(LocalDateTime.now().minusDays(1).withHour(15).withMinute(0).withNano(0));
        scheduling4_past.setEndTime(LocalDateTime.now().minusDays(1).withHour(16).withMinute(0).withNano(0));
        scheduling4_past.setStatus(SchedulingStatus.COMPLETED);
        scheduling4_past.setClient(client1);

        return new ArrayList<>(Arrays.asList(scheduling1_booked, scheduling2_booked, scheduling3_available, scheduling4_past));
    }



}