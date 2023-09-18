package com.daalgae.daalgaeproject.payment.paycontroller;

import com.daalgae.daalgaeproject.payment.dto.KakaoApprove;
import com.daalgae.daalgaeproject.payment.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public String Success(@RequestParam("pg_token") String pgToken) {

        KakaoApprove res = kakaoPayService.approve(pgToken);

        /*요청 결과에 대해서 데이터 베이스에 저장 또는 업데이트 할 로직 추가 */

        return "payment/success";
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

