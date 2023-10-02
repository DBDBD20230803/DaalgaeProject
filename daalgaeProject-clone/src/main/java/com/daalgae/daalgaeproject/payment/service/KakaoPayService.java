package com.daalgae.daalgaeproject.payment.service;


import com.daalgae.daalgaeproject.member.model.dao.MemberDAO;
import com.daalgae.daalgaeproject.member.model.dto.UserImpl;
import com.daalgae.daalgaeproject.payment.dao.OrderPayMapper;
import com.daalgae.daalgaeproject.payment.dto.*;
import groovy.util.logging.Log4j2;
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
@Log4j2
public class KakaoPayService {
    static final String cid = "TC0ONETIME";
    static final String admin_Key = "7785656bcca2241ab970a65883853a10";

    private OrderPayMapper orderPayMapper;
    private MemberDAO memberMapper;
    private KakaoReady kakaoReady;
    private KakaoPayService kakaoPayService;


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
        parameters.add("partner_order_id", "Dogtype");
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
        payParams.add("partner_order_id", "Dogtype");
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

        //결제 데이터 insert 했음
        orderPayMapper.orderRegist(orderPay);

        try {
            // userCode 가져와~~~~~
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            Object principal = authentication.getPrincipal();
            int memCode = 0;
            if (principal instanceof UserImpl) {
                UserImpl user = (UserImpl) principal;
                memCode = user.getMemCode();
                System.out.println("가왓냐 ? 가왔냐 ?????? : " + memCode);

                if (user != null) {
                    int currentDogGum = user.getMemDogGum();

                    int newDogGum = currentDogGum + orderPay.getMemDogGum();

                    user.setMemDogGum(newDogGum);
                    memberMapper.updateMemDogGum(user);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("payment processing failed : " + e.getMessage(), e);
        }
    }


    public List<OrderPay> getAllPayment(Integer memCode) {
        System.out.println("서비스임 ... 날짜데이타ㅓ.." + memCode);
        return orderPayMapper.userSelect(memCode);
    }

    public List<UseHistory> getUserAllFind (int dogGumUseCode) {
        System.out.println("userTable 가져오냐 ? : " + dogGumUseCode);
        return orderPayMapper.userAllFind(dogGumUseCode);
    }

    }
