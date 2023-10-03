package com.daalgae.daalgaeproject.payment.controller;

import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.payment.dto.KakaoApprove;
import com.daalgae.daalgaeproject.payment.dto.OrderPay;
import com.daalgae.daalgaeproject.webtoon.model.dto.UseHistory;
import com.daalgae.daalgaeproject.payment.service.KakaoPayService;
import com.daalgae.daalgaeproject.webtoon.service.WebtoonService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("payment")
@RequiredArgsConstructor
@Slf4j
@Data
public class KakaoPayController {


    private final KakaoPayService kakaoPayService;
    private final WebtoonService webtoonService;

    @PostMapping("payments")
    public String goPayment(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            int member = webtoonService.getMemberByMemCode(memCode);

            model.addAttribute("member", member);
        }

        return "payment/payments";
    }


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
    public String Success(KakaoApprove kakaoApprove, @RequestParam("pg_token") String pgToken, Model model) {

        kakaoApprove = kakaoPayService.approve(pgToken);


        System.out.println("값은 다 담아왔냐 ?");
        System.out.println(kakaoApprove);

//        kakaoPayService.dogGumPay(OrderPay);

        OrderPay orderPay = new OrderPay();

        orderPay.setDogGumPayTid(kakaoApprove.getTid());
        orderPay.setDogGumPayMethod(kakaoApprove.getPayment_method_type());
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            int member = webtoonService.getMemberByMemCode(memCode);

            model.addAttribute("member", member);
        }

        return "webtoon/dengInfo";
    }

    @GetMapping("useHistory")
    public String useHistory(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            int member = webtoonService.getMemberByMemCode(memCode);
            System.out.println(member);
            List<OrderPay> orderPays = kakaoPayService.getAllPayment(memCode);
            System.out.println("날짜 제대로 가져오냐 ?: " + orderPays);
            model.addAttribute("member", member);
            model.addAttribute("orderPays", orderPays);

            if (orderPays.isEmpty()) {
                return "redirect:/payment/useHistoryEmpty";
            }
        }
        return "payment/useHistory";
    }

    @GetMapping("useHistoryEmpty")
    public String goUseHistory(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            int member = webtoonService.getMemberByMemCode(memCode);

            model.addAttribute("member", member);
        }
        return "payment/useHistoryEmpty";
    }

    @GetMapping("/userDogGum")
    public String purchaseDogGum(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UserImpl) {
            UserImpl user = (UserImpl) authentication.getPrincipal();
            int memCode = user.getMemCode();

            int member = webtoonService.getMemberByMemCode(memCode);

            int dogGumUseCode = 0 ;
            List<UseHistory> useDogGumList = kakaoPayService.getUserAllFind(dogGumUseCode);

            model.addAttribute("member", member);
            model.addAttribute("useDogGumList", useDogGumList);

        }
        return "payment/purchaseDogGum";
    }
}

