package com.mudit.paymentservice.controllers;

import com.mudit.paymentservice.dtos.GeneratePaymentLinkRequestDto;
import com.mudit.paymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(@Qualifier("stripe") PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto generatePaymentLinkRequestDto) throws StripeException, RazorpayException {
        // return payment link
        return paymentService.generatePaymentLink(generatePaymentLinkRequestDto.orderId);
    }

    @PostMapping("/webhooks") // PG will call this api on any change in payment status
    public void handleWebHook(@RequestBody Object object){
        // here is where you want to decide what you want to do
        // you can go to razorpay dashboard and set these webhooks
    }

}