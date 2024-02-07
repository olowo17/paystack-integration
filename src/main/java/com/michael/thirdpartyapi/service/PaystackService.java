package com.michael.thirdpartyapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.michael.thirdpartyapi.model.PaymentPaystack;
import com.michael.thirdpartyapi.model.dto.CreatePlanDto;
import com.michael.thirdpartyapi.model.dto.InitializePaymentDto;
import com.michael.thirdpartyapi.model.response.CreatePlanResponse;
import com.michael.thirdpartyapi.model.response.InitializePaymentResponse;
import com.michael.thirdpartyapi.model.response.PaymentVerificationResponse;

public interface PaystackService {
   CreatePlanResponse createPlan(CreatePlanDto createPlanDto) throws Exception;
    InitializePaymentResponse initializePayment(InitializePaymentDto initializePaymentDto);

    PaymentVerificationResponse paymentVerification(String reference, Long id) throws JsonProcessingException;

}
