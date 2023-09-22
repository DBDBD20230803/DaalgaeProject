package com.daalgae.daalgaeproject.payment.service;


import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.payment.dao.OrderPayMapper;
import com.daalgae.daalgaeproject.payment.dto.KakaoApprove;
import com.daalgae.daalgaeproject.payment.dto.KakaoReady;
import com.daalgae.daalgaeproject.payment.dto.OrderPay;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoPayService {
    static final String cid = "TC0ONETIME";
    static final String admin_Key = "7785656bcca2241ab970a65883853a10";

    private OrderPayMapper orderPayMapper;
    private MemberDAO memberMapper;
    private KakaoReady kakaoReady;


    @Autowired
    public KakaoPayService(OrderPayMapper orderPayMapper, MemberDAO memberDAO) {
        this.orderPayMapper = orderPayMapper;
        this.memberMapper = memberDAO;
    }


    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    // 요기가 지금 결제 준비 단계!!
    public KakaoReady kakaoPayReady(String itemName, int quantity, int totalAmount) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        int memCode = 0;
        if (principal instanceof UserImpl) {
            UserImpl user = (UserImpl) principal;
            memCode = user.getMemCode();
            System.out.println("가왓냐 ? 가왔냐 ??????" + memCode);
        }

        /*Object memCode = null;
        if (authentication != null && authentication.isAuthenticated()) {
            memCode = authentication.getPrincipal();
            System.out.println("드디어 가져왔냐 ?" + memCode);
        }*/

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();

        parameters.add("cid", cid);
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", memCode);
        parameters.add("item_name", itemName);
        parameters.add("quantity", String.valueOf(quantity));
        parameters.add("total_amount", String.valueOf(totalAmount));
        parameters.add("vat_amount", "0");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8001/payment/success");
        parameters.add("cancel_url", "http://localhost:8001/payment/cancel");
        parameters.add("fail_url", "http://localhost:8001/payment/fail");

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(parameters, this.getHeaders());

        //외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReady.class);
        return kakaoReady;

    }


    //결제 완료 승인
    public KakaoApprove approve(String pgToken) {


        HttpHeaders headers = new HttpHeaders();
        String auth = "KakaoAK " + admin_Key;
        headers.set("Authorization", auth);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        int memCode = 0;
        if (principal instanceof UserImpl) {
            UserImpl user = (UserImpl) principal;
            memCode = user.getMemCode();
            System.out.println("가왓냐 ? 가왔냐 ?????? : " + memCode);
        }


        // 카카오 요청
        MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<>();

        payParams.add("cid", cid);
        payParams.add("tid", kakaoReady.getTid());
        payParams.add("partner_order_id", "가맹점 주문 번호");
        payParams.add("partner_user_id", memCode);
        payParams.add("pg_token", pgToken);

        HttpEntity<Map> requset = new HttpEntity<>(payParams, headers);
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";

        KakaoApprove res = restTemplate.
                postForObject(url, requset, KakaoApprove.class);

        return res;
    }

    // 헤더값
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return httpHeaders;

    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void orderTranscation(OrderPay orderPay) {
        System.out.println("인서트 하고,");
        System.out.println(orderPay);
        orderPayMapper.orderRegist(orderPay);

/*
        List<Integer> memberCode = orderPay.getMember()
                .stream()
                .map(MemberDTO::getMemCode)
                .collect(Collectors.toList());
        System.out.println("memberCode = " + memberCode);


        Map<String, List<Integer>> map = new HashMap<>();
        map.put("memberCode", memberCode);

        List<MemberDTO> members = memberMapper.memDogGum(map);
        System.out.println("members =" + members);

int totalDogGum = addDogGum(orderPay.getMember());
        System.out.println("totalDogGum =" + totalDogGum);

        for (MemberDTO member : members) {
            member.setMemDogGum((member.getMemDogGum() + totalDogGum));
        }

        for (MemberDTO member : members) {
            memberMapper.updateMemDogGum(member);
        }
    }

    private int addDogGum(List<MemberDTO> orderPays) {
        int totalDogGum = 0;
        for (MemberDTO orderPay : orderPays) {
            totalDogGum += orderPay.getMemDogGum();
        }
        return totalDogGum;
    }
*/


    }
}