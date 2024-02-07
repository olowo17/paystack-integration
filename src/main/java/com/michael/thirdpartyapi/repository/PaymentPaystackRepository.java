package com.michael.thirdpartyapi.repository;

import com.michael.thirdpartyapi.model.PaymentPaystack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentPaystackRepository  extends JpaRepository <PaymentPaystack, Long> {
}
