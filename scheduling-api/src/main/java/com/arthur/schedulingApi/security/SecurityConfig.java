package com.arthur.schedulingApi.security;

import com.arthur.schedulingApi.security.jwt.SecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/findAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/services").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/services/{id}").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/services/{id}").hasRole("MANAGER") // Regra que faltava
                        .requestMatchers(HttpMethod.GET, "/api/v1/services/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/scheduling/{id}").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/scheduling/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/scheduling/{id}").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/scheduling/join/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/v1/scheduling/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/payments/create-payment-intent").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/stripe-webhooks").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
