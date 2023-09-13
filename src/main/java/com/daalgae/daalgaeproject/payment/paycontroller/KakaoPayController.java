package com.daalgae.daalgaeproject.payment.paycontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("payment")
public class KakaoPayController {
    @GetMapping("payments")
    public String goPayment() {
        System.out.println("aaaa");
        return "payment/payments";

    }
}

