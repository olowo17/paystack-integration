package com.michael.thirdpartyapi.controller;

import com.michael.thirdpartyapi.model.dto.CreatePlanDto;
import com.michael.thirdpartyapi.model.dto.InitializePaymentDto;
import com.michael.thirdpartyapi.model.response.CreatePlanResponse;
import com.michael.thirdpartyapi.model.response.InitializePaymentResponse;
import com.michael.thirdpartyapi.model.response.PaymentVerificationResponse;
import com.michael.thirdpartyapi.service.PaystackService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/paystack", produces = MediaType. APPLICATION_JSON_VALUE)
public class PaystackController {

    private final PaystackService paystackService;

    public PaystackController(PaystackService paystackService) {
        this.paystackService = paystackService;
    }

    @PostMapping("/createplan")
    public CreatePlanResponse createPlan (@Validated @RequestBody CreatePlanDto createPlanDto) throws Exception {
        return paystackService.createPlan(createPlanDto);
    }

    @PostMapping("/initializepayment")
    public InitializePaymentResponse initializePayment(@Validated @RequestBody InitializePaymentDto initializePaymentDto) throws Throwable {
        return paystackService.initializePayment(initializePaymentDto);
    }

    @GetMapping("/verify-payment/{reference}/plan/{id}")
    public PaymentVerificationResponse paymentVerification(@PathVariable(value = "reference") String reference,
                                                      @PathVariable(value = "id") Long id)
            throws Exception {

        return paystackService.paymentVerification(reference, id);
    }
}