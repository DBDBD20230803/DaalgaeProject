package com.daalgae.daalgaeproject.payment.paycontroller;

import com.daalgae.daalgaeproject.payment.dto.KakaoApprove;
import com.daalgae.daalgaeproject.payment.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("payment")
@RequiredArgsConstructor
public class KakaoPayController {


    private final KakaoPayService kakaoPayService;


    // 결제 요청??
    @PostMapping("ready")
    public String readyToKakaoPay(Model model
            , @RequestParam String itemName
            , @RequestParam int quantity
            , @RequestParam int totalAmount) {

        model.addAttribute("itemName", itemName);
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalAmount", totalAmount);

        return "redirect:" + kakaoPayService
                .kakaoPayReady(itemName, quantity, totalAmount)
                .getNext_redirect_pc_url();
    }


    //결제완료 승인
    @GetMapping("success")
    public String Success( KakaoApprove kakaoApprove, @RequestParam("pg_token") String pgToken) {

        kakaoApprove = kakaoPayService.approve(pgToken);


        System.out.println("값은 다 담아왔냐 ?");
        System.out.println(kakaoApprove);
        kakaoPayService.registKakaoPay(kakaoApprove);
        System.out.println("나 실행됐냐 ??");

        /*res.getTid();*/
        System.out.println("나 디비에좀 들어가자 ㅡㅡ");


        return "payment/successPage";
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
