package com.daalgae.daalgaeproject.payment.paycontroller;

import com.daalgae.daalgaeproject.payment.dto.KakaoReadyResponse;
import com.daalgae.daalgaeproject.payment.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("payment")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;


    // 결제 요청??
    @GetMapping("ready")
    public String readyToKakaoPay () {

        System.out.println("여기까지 왔나 ?");

        return "redirect:" + kakaoPayService.kakaoPayReady().getNext_redirect_pc_url();
    }
}

