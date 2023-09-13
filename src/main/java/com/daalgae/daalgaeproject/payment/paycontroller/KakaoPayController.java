package com.daalgae.daalgaeproject.payment.paycontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("payment/")
@RequiredArgsConstructor
public class KakaoPayController {

    @GetMapping("Payment")
    public String goPayment() {
        return "payment/Payment";
    }
}

