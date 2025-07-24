package com.arthur.schedulingApi.usecases;

import com.arthur.schedulingApi.models.Scheduling;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String userEmailSender;

    @Async
    public void sendSchedulingConfirmedEmail(Scheduling scheduling) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(userEmailSender);
            message.setTo(scheduling.getClient().getEmail());
            message.setSubject("Confirmação de Agendamento - " + scheduling.getServices().getName());

            String text = String.format(
                    "Olá, %s!\n\n" +
                            "Seu agendamento foi confirmado com sucesso.\n\n" +
                            "Detalhes do Agendamento:\n" +
                            "Serviço: %s\n" +
                            "Data: %s\n" +
                            "Horário: %s\n" +
                            "Local: %s\n\n" +
                            "Obrigado por escolher nossos serviços!",
                    scheduling.getClient().getName(),
                    scheduling.getServices().getName(),
                    scheduling.getStartTime().toLocalDate().toString(),
                    scheduling.getStartTime().toLocalTime().toString(),
                    scheduling.getServices().getLocation()
            );
            message.setText(text);

            mailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
