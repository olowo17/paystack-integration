package com.michael.thirdpartyapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.michael.thirdpartyapi.UserNotFoundException;
import com.michael.thirdpartyapi.model.PaymentPaystack;
import com.michael.thirdpartyapi.model.PricingPlanType;
import com.michael.thirdpartyapi.model.User;
import com.michael.thirdpartyapi.model.dto.CreatePlanDto;
import com.michael.thirdpartyapi.model.dto.InitializePaymentDto;

import com.michael.thirdpartyapi.model.response.CreatePlanResponse;
import com.michael.thirdpartyapi.model.response.InitializePaymentResponse;
import com.michael.thirdpartyapi.model.response.PaymentVerificationResponse;
import com.michael.thirdpartyapi.repository.PaymentPaystackRepository;
import com.michael.thirdpartyapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Date;

import static com.michael.thirdpartyapi.constant.APIConstants.*;

@Service
@RequiredArgsConstructor
public class PaystackServiceImp  implements  PaystackService{
    private final PaymentPaystackRepository paystackRepository;
    private final UserRepository userRepository;

    @Value("${api_secret}")
    private String paystackSecretKey;
    @Override
    public CreatePlanResponse createPlan(CreatePlanDto createPlanDto) throws Exception {
        CreatePlanResponse createPlanResponse = null;

        try {
            WebClient webClient = WebClient.create();
            // Convert the DTO object to JSON string
            String jsonPayload = new Gson().toJson(createPlanDto);
            // Make a POST request with WebClient
            Mono<CreatePlanResponse> responseMono = webClient.post()
                    .uri(PAYSTACK_INIT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + paystackSecretKey)
                    .bodyValue(jsonPayload)
                    .retrieve()
                    .bodyToMono(CreatePlanResponse.class);

            // Block and get the response
            createPlanResponse = responseMono.block();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        return createPlanResponse;

    }

    @Override
    public InitializePaymentResponse initializePayment(InitializePaymentDto initializePaymentDto) {
        InitializePaymentResponse initializePaymentResponse = null;

        try {
            WebClient client = WebClient.create();

            // Convert the DTO object to JSON string
            String jsonPayload = new Gson().toJson(initializePaymentDto);

            // Make a POST request with WebClient
            Mono<String> responseMono = client.post()
                    .uri(PAYSTACK_INITIALIZE_PAY)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + paystackSecretKey)
                    .bodyValue(jsonPayload)
                    .retrieve()
                    .bodyToMono(String.class);

            // Block and get the response body
            String responseBody = responseMono.block();

            // Parse the JSON response into InitializePaymentResponse object
            ObjectMapper mapper = new ObjectMapper();
            initializePaymentResponse = mapper.readValue(responseBody, InitializePaymentResponse.class);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        return initializePaymentResponse;
    }

//    @Override
//    public PaymentVerificationResponse paymentVerification(String reference, String plan, Long id) throws Exception {
//        PaymentVerificationResponse paymentVerificationResponse = null;
//        PaymentPaystack paymentPaystack = null;
//
//        try {
//            WebClient client = WebClient.create();
//
//            // Make a GET request with WebClient
//            Mono<PaymentVerificationResponse> responseMono = client.get()
//                    .uri(PAYSTACK_VERIFY + reference)
//                    .header("Authorization", "Bearer " + paystackSecretKey)
//                    .retrieve()
//                    .bodyToMono(PaymentVerificationResponse.class);
//
//            // Block and get the response
//            paymentVerificationResponse = responseMono.block();
//
//            if (paymentVerificationResponse == null || !"success".equals(paymentVerificationResponse.getData().getStatus())) {
//                throw new Exception("An error");
//            } else {
//                User appUser = userRepository.getById(id);
//                PricingPlanType pricingPlanType = PricingPlanType.valueOf(plan.toUpperCase());
//
//                paymentPaystack = PaymentPaystack.builder()
//                        .user(appUser)
//                        .reference(paymentVerificationResponse.getData().getReference())
//                        .amount(paymentVerificationResponse.getData().getAmount())
//                        .gatewayResponse(paymentVerificationResponse.getData().getGatewayResponse())
//                        .paidAt(paymentVerificationResponse.getData().getPaidAt())
//                        .createdAt(paymentVerificationResponse.getData().getCreatedAt())
//                        .channel(paymentVerificationResponse.getData().getChannel())
//                        .currency(paymentVerificationResponse.getData().getCurrency())
//                        .ipAddress(paymentVerificationResponse.getData().getIpAddress())
//                        .planType(pricingPlanType)
//                        .createdOn(new Date())
//                        .build();
//            }
//        } catch (Exception ex) {
//            throw new Exception("Paystack", ex);
//        }
//
//        paystackRepository.save(paymentPaystack);
//        return paymentVerificationResponse;
//    }


    @Override

        public PaymentVerificationResponse paymentVerification(String reference, Long id) throws JsonProcessingException {
            PaymentVerificationResponse paymentVerificationResponse = null;
            PaymentPaystack paymentPaystack = null;

                WebClient client = WebClient.create();

                // Make a GET request with WebClient
                Mono<String> responseMono = client.get()
                        .uri(PAYSTACK_VERIFY + reference)
                        .header("Authorization", "Bearer " + paystackSecretKey)
                        .retrieve()
                        .bodyToMono(String.class);

                 String responseBody = responseMono.block();

        // Parse the JSON response into InitializePaymentResponse object
        ObjectMapper mapper = new ObjectMapper();
        paymentVerificationResponse = mapper.readValue(responseBody, PaymentVerificationResponse.class);
        return paymentVerificationResponse;

                // Block and get the response
//                paymentVerificationResponse = responseMono.block();
//
//                    User appUser = userRepository.findById(id).orElseThrow( ()->
//                            new UserNotFoundException("User not found"));
////                    PricingPlanType pricingPlanType = PricingPlanType.valueOf(plan.toUpperCase());
//
//                    paymentPaystack = PaymentPaystack.builder()
//                            .user(appUser)
//                            .reference(paymentVerificationResponse.getData().getReference())
//                            .amount(paymentVerificationResponse.getData().getAmount())
//                            .gatewayResponse(paymentVerificationResponse.getData().getGatewayResponse())
//                            .paidAt(paymentVerificationResponse.getData().getPaidAt())
//                            .createdAt(paymentVerificationResponse.getData().getCreatedAt())
//                            .channel(paymentVerificationResponse.getData().getChannel())
//                            .currency(paymentVerificationResponse.getData().getCurrency())
//                            .ipAddress(paymentVerificationResponse.getData().getIpAddress())
//                            .createdOn(new Date())
//                            .build();
//
//
//            paystackRepository.save(paymentPaystack);
//            return paymentVerificationResponse;
        };

}
