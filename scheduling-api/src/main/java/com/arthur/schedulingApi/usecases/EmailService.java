package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.models.Scheduling;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String userEmailSender;

    @Async
    public void sendConfirmationEmail(String clientEmail, String clientName, String serviceName, LocalDateTime startTime, String location) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(userEmailSender);
        message.setTo(clientEmail);
        message.setSubject("Confirmação de Agendamento - " + serviceName);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String text = String.format(
                "Olá, %s!\n\n" +
                        "Seu agendamento foi confirmado com sucesso.\n\n" +
                        "Detalhes do Agendamento:\n" +
                        "Serviço: %s\n" +
                        "Data: %s\n" +
                        "Horário: %s\n" +
                        "Local: %s\n\n" +
                        "Obrigado por escolher nossos serviços!",
                clientName,
                serviceName,
                startTime.format(dateFormatter),
                startTime.format(timeFormatter),
                location
        );
        message.setText(text);

        mailSender.send(message);
    }
}
