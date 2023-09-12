package com.daalgae.daalgaeproject.payment.service;

import com.daalgae.daalgaeproject.payment.dto.KakaoApproveResponse;
import com.daalgae.daalgaeproject.payment.dto.KakaoCancelResponse;
import com.daalgae.daalgaeproject.payment.dto.KakaoReadyResponse;
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
public class KakaoPayService {

    static final String cid = "TC0ONETIME"; // 테스트 코드 실제 가맹점이 존재할 경우 카카오페이에서 발급 받은 가맹점 코드 입력
    static final String admin_Key = "${ADMIN_KEY}";
    private KakaoReadyResponse kakaoReady;

    public KakaoReadyResponse kakaoPayReady() {

        // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("item_name", "상품명");
        parameters.add("quantity", "주문 수량"); //금액별 결제로 인해 1개가 고정값
        parameters.add("total_amount", "총 금액");
        parameters.add("vat_amount", "부가세"); //부가세 포함 한 가격으로 결제 진행중
        parameters.add("tax_free_amount", "상품 비과세 금액");
        parameters.add("approval_url", "http://localhost:8080/payment/success");// 결제 성공시
        parameters.add("cancel_url", "http://localhost:8080/payment/cancel"); // 결제 취소시
        parameters.add("fail_url", "http://localhost:8080/payment/fail");


        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);
        return kakaoReady;
    }

    //결제 완료 승인
    public KakaoApproveResponse approveResponse(String pgToken) {
        //카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("pgToken", pgToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "http://kapi.kakao.cokm/v1/patment/approve",
                requestEntity,
                KakaoApproveResponse.class);
        return approveResponse;
    }

    //결제환불
    public KakaoCancelResponse kakaoCancel() {

        //카카오페이지 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", "환불할 결제 고유 번호");
        parameters.add("cancel_amount", "환불 금액");
        parameters.add("cancel_tax_free_amount", "환불 비과세 금액");
        parameters.add("cancel_vat_amount", "환불 부가세");
        //파라미터 헤더
        HttpEntity<MultiValueMap<String, String>> requsetEntity = new HttpEntity<>(parameters, this.getHeaders());
        RestTemplate restTemplate = new RestTemplate();

        KakaoCancelResponse cancelResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/patment/cancel",
                requsetEntity,
                KakaoCancelResponse.class);
        return cancelResponse;
    }

    //카카오 요구 헤더값
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK" + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return httpHeaders;
    }




}
