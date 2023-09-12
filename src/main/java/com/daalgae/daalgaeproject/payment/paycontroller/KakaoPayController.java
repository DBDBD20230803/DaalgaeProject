package com.daalgae.daalgaeproject.payment.paycontroller;

import com.daalgae.daalgaeproject.payment.dto.KakaoApproveResponse;
import com.daalgae.daalgaeproject.payment.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    /*결제 요청*/

    @PostMapping("/ready")
    public ResponseEntity readyToKakaPay() {

    return null;
    }

    //결제 성공
    public ResponseEntity afterPayRequest(@RequestParam("pg_Token") String pgToken) {
        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken);

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }


/*    // 결제 진행 중 취소
    @GetMapping("/cancel")
    public void cancel() {
        throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
    }
    // 결제 실패
    public void fail() {
        throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
    }*/
}

