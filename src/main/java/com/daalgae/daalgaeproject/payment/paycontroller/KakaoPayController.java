package com.daalgae.daalgaeproject.payment.paycontroller;

import com.daalgae.daalgaeproject.payment.dto.KakaoApproveResponse;
import com.daalgae.daalgaeproject.payment.dto.KakaoReadyResponse;
import com.daalgae.daalgaeproject.payment.service.KakaoPayService;
import groovy.util.Expando;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("payment")
@RequiredArgsConstructor
public class KakaoPayController {

    @Autowired
    private KakaoApproveResponse result;

    @Autowired
    private final KakaoPayService kakaoPayService;
    private String pgToken;
    private Model model;


    // 결제 요청??
    @PostMapping("ready")

    public String readyToKakaoPay(Model model
            , @RequestParam String itemName
            , @RequestParam int quantity
            , @RequestParam int totalAmount) {

        model.addAttribute("itemName", itemName);
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalAmount", totalAmount);


        System.out.println(result);
        return "redirect:" + kakaoPayService
                .kakaoPayReady(itemName, quantity, totalAmount)
                .getNext_redirect_pc_url();
    }

    @PostMapping("/success")
    public ResponseEntity paymentSuccess  (@RequestParam("pg_token") String pgToken, Model model){

        KakaoApproveResponse kakaoApprove = kakaoPayService.kakaoApproveResponse(pgToken);

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);

    }

    //결제 진행 중 취소
    @GetMapping("/cancel")
    public ResponseEntity cancel() {

        return ResponseEntity.ok("결제가 취소 되었습니다.");
    }

    //결제 실패
    public ResponseEntity fail() {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제에 실패하였습니다.");


        //실패 페이지 있을경우 -> return "redirect: /payment/fail";
    }

}

