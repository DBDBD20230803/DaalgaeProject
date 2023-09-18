package com.daalgae.daalgaeproject.payment.service;


import com.daalgae.daalgaeproject.payment.dto.KakaoApproveResponse;
import com.daalgae.daalgaeproject.payment.dto.KakaoReadyResponse;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KakaoPayService {
    static final String cid = "TC0ONETIME";
    static final String admin_Key = "7785656bcca2241ab970a65883853a10";

    private KakaoApproveResponse result;
    private KakaoReadyResponse kakaoReady;


    // 요기가 지금 결제 준비 단계!!
    public KakaoReadyResponse kakaoPayReady(String itemName, int quantity, int totalAmount) {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("cid", cid);
        parameters.add("partner_order_id", "가맹점 주문번호");
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("item_name", itemName);
        parameters.add("quantity", String.valueOf(quantity));
        parameters.add("total_amount", String.valueOf(totalAmount));
        parameters.add("vat_amount", "0");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8001/payment/success");
        parameters.add("cancel_url", "http://localhost:8001/payment/cancel");
        parameters.add("fail_url", "http://localhost:8001/payment/fail");
        System.out.println(totalAmount);

            // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity
                = new HttpEntity<>(parameters, this.getHeaders());

        //외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("외부 url 요청 완료 ?");
        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);
        System.out.println("맞다공");
        System.out.println(kakaoReady);
        return kakaoReady;

    }


    //결제 완료 승인
    public KakaoApproveResponse kakaoApproveResponse(String pgToken) {
        // 카카오 요청 ??

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", "가맹점 회원ID");
        parameters.add("pg_token", pgToken);

        System.out.println("값 받아옴 ?");

        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve"
                ,requestEntity
                , KakaoApproveResponse.class);
        System.out.println("여기는 왔니 ??");
        return approveResponse;
    }
    // 헤더값
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return httpHeaders;

    }
}
