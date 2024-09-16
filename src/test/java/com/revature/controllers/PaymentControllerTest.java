package com.revature.controllers;

import java.util.List;

import com.revature.models.PaymentEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentControllerTest {

    @Autowired
    PaymentController paymentController;

    @Test
    public void testGetAllPayments() {
        List<PaymentEntity> payments = paymentController.getAllPayments();
        for (PaymentEntity payment : payments) {
            assert payment.getPaymentTxnId() != null;
            assert payment.getFailureReason() != null;
            assert payment.getPaymentTxnSuccess() != null;
        }
    }
}
