package com.arthur.schedulingApi.payment.stripe.usecases;

import com.arthur.schedulingApi.models.Scheduling;
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

    public String createPaymentIntentForPendingScheduling(PaymentRequestDTO request) throws StripeException {

        //Scheduling scheduling = findScheduling.findSchedulingAsModel(request.schedulingId());
        long amountInCents = 50000L; //scheduling.getServices().getPriceInCents();

        String currency = request.currency();

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amountInCents)
                        .setCurrency(currency)
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                        .setEnabled(true)
                                        .setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER) // <-- ADICIONE ESTA LINHA
                                        .build()
                        )
                        .putMetadata("scheduling_id", request.schedulingId().toString())
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent.getClientSecret();
    }
}
