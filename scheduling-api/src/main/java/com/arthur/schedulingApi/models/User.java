package com.arthur.schedulingApi.models;

import com.arthur.schedulingApi.models.enums.UserRoles;
import com.arthur.schedulingApi.models.ratingImpl.UserRating;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.averagingInt;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false , unique = true)
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
    private List<Services> servicesOwned = new ArrayList<>();

    @OneToMany(mappedBy = "appraiser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRating> ratingsGiven = new ArrayList<>();

    @OneToMany(mappedBy = "ratee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRating> ratingsReceived = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Scheduling> schedulings = new ArrayList<>();

    public User(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public void setEncodePassword(String rawPassword, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(rawPassword);
    }

    @Transient
    public double updateScore(){
        if(this.ratingsReceived != null){
            DecimalFormat format = new DecimalFormat("#.##");

            double number = this.ratingsReceived.stream()
                    .collect(averagingInt(UserRating::getScore));

            return Double.parseDouble(format.format(number));
        }
        else{
            return 0;
        }
    }

}
