package com.arthur.schedulingApi.payment.stripe.usecases;

import com.arthur.schedulingApi.payment.stripe.dto.PaymentRequestDTO;
import com.arthur.schedulingApi.usecases.FindScheduling;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentUseCase {

    private final FindScheduling findScheduling;

    public String createPaymentIntent(PaymentRequestDTO request) throws StripeException {

        long amount = 5000;

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount)
                        .setCurrency(request.currency().toLowerCase())
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return paymentIntent.getClientSecret();
    }
}
