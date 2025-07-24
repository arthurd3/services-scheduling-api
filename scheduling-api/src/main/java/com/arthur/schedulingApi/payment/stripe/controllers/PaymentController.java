package com.arthur.schedulingApi.payment.stripe.controllers;


import com.arthur.schedulingApi.payment.stripe.dto.PaymentRequestDTO;
import com.arthur.schedulingApi.payment.stripe.dto.PaymentResponseDTO;
import com.arthur.schedulingApi.payment.stripe.usecases.PaymentUseCase;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<PaymentResponseDTO> createPaymentIntent(@RequestBody PaymentRequestDTO request) {
        try {
            String clientSecret = paymentUseCase.createPaymentIntent(request);
            return ResponseEntity.ok(new PaymentResponseDTO(clientSecret));

        } catch (StripeException e) {

            return ResponseEntity.badRequest().build();
        }
    }
}