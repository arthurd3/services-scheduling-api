package com.arthur.schedulingApi.payment.stripe.dto;

public record PaymentRequestDTO(Long schedulingId, String currency) {}
