package com.daalgae.daalgaeproject.payment.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class KakaoPayController {

    @GetMapping("payments")
    public String goPayment() {
        System.out.println("aaaa");
        return "payment/payments";

    }
}
