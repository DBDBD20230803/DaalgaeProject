package com.daalgae.daalgaeproject.payment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class KakaoReadyResponse {
 // 결제 요청시 카카오에게 받음
    private String tid;
    private String next_redirect_pc_url;
    private String created_at;
}
