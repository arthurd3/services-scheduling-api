package com.arthur.schedulingApi.payment.stripe.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.api.secretKey}")
    private String secretKey;

    @PostConstruct
    public void initStripe() {
        Stripe.apiKey = secretKey;
    }
}