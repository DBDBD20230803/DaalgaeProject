package com.daalgae.daalgaeproject.payment.service;


import com.daalgae.daalgaeproject.payment.dao.ApproveMapper;
import com.daalgae.daalgaeproject.payment.dto.KakaoApprove;
import com.daalgae.daalgaeproject.payment.dto.KakaoReady;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoPayService {
    static final String cid = "TC0ONETIME";
    static final String admin_Key = "7785656bcca2241ab970a65883853a10";

    private final ApproveMapper approveMapper;
    private KakaoReady kakaoReady;



    // 요기가 지금 결제 준비 단계!!
    public KakaoReady kakaoPayReady(String itemName, int quantity, int totalAmount) {

        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();

        parameters.add("cid", cid);
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", "가맹점 회원 ID");
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

        // 카카오 요청
        MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<>();

        payParams.add("cid", cid);
        payParams.add("tid", kakaoReady.getTid());
        payParams.add("partner_order_id", "가맹점 주문 번호");
        payParams.add("partner_user_id", "가맹점 회원 ID");
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

    @Transactional
    public void registKakaoPay(KakaoApprove kakaoApprove) {
        System.out.println("나왔냐 ?");
        System.out.println(kakaoApprove);
        approveMapper.registKakaoPay(kakaoApprove);
    }
}
