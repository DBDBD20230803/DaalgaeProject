package com.daalgae.daalgaeproject.payment.service;


import com.daalgae.daalgaeproject.payment.dto.KakaoReadyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {
    static final String cid = "TC0ONETIME";
    static final String admin_Key = "7785656bcca2241ab970a65883853a10";
    private KakaoReadyResponse kakaoReady;
    // 요기가 지금 결제 준비 단계?ㅁ
    public KakaoReadyResponse kakaoPayReady() {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("cid", cid);
        parameters.add("partner_order_id", "가맹점 주문번호");
        parameters.add("partner_user_id", "가맹점 회원 ID");
        parameters.add("item_name", "개껌");
        parameters.add("quantity","1");
        parameters.add("total_amount", "1100");
        parameters.add("vat_amount", "100");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8001/payment/success");
        parameters.add("cancel_url", "http://localhost:8001");
        parameters.add("fail_url", "http://localhost:8001");


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


    // 헤더값
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        System.out.println("지나가요~");
        return httpHeaders;

    }
}
