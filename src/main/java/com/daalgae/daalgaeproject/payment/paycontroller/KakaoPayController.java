package com.daalgae.daalgaeproject.payment.paycontroller;

import com.daalgae.daalgaeproject.payment.dto.KakaoApprove;
import com.daalgae.daalgaeproject.payment.dto.OrderPay;
import com.daalgae.daalgaeproject.payment.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("payment")
@RequiredArgsConstructor
@Slf4j
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

        System.out.println("갑자기 여기도 못오냐 ?");

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

//        kakaoPayService.dogGumPay(OrderPay);

        OrderPay orderPay = new OrderPay();

        orderPay.setDogGumPayCode(kakaoApprove.getTid());
        orderPay.setDogGumPaymethod(kakaoApprove.getPayment_method_type());
        orderPay.setDogGumPay(String.valueOf(kakaoApprove.getAmount().getTotal()));
        orderPay.setDogGumPayDate(kakaoApprove.getCreated_at());
        orderPay.setDogItemName(kakaoApprove.getItem_name());
        orderPay.setRefMemCode(Integer.parseInt(kakaoApprove.getPartner_user_id()));
        orderPay.setMemDogGum(kakaoApprove.getQuantity());



        System.out.println("나 실행됐냐 ??");

        System.out.println(orderPay);

        /*res.getTid();*/
        System.out.println("나 디비에좀 들어가자 ㅡㅡ");
        kakaoPayService.orderTranscation(orderPay);

        return "webtoon/dengInfo";
    }


    //결제 진행 중 취소
    @GetMapping("/cancel")
    public ResponseEntity cancel() {

        return ResponseEntity.ok("결제가 취소 되었습니다.");
    }

    //결제 실패

    @GetMapping("/fail")
    public ResponseEntity fail() {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제에 실패하였습니다.");


        //실패 페이지 있을경우 -> return "redirect: /payment/fail";
    }

    @GetMapping("list")
    public String list(Model model) {
        System.out.println(" 여기도 못왔냐 ?");
//        List<OrderPay> k = kakaoPayService.findAllPay();

//        System.out.println("디비에서 값꺼내왔냐 ?" + k);
//        model.addAttribute("k", k);
//        System.out.println(k);
        return "payment/successPage";
    }


}

