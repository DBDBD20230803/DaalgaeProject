package com.daalgae.daalgaeproject.payment.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoApproveResponse {
    private String aid; // 요청 고유 번호
    private String tid; // 결제 고유 ㅂjsgh
    private String cid; // 가맹점 코드
    private String sid; //정기결제용 ID
    private String partner_order_id;// 가맹점 주문번호
    private String partner_user_id; //가맹점 회원 id
    private String patment_method_type; //결제 수단
    private Amount amount; //결제 금액 정보
    private String item_name;// 상품명
    private String item_code;// 상품코드
    private int quzntity; // 상품수령
    private String created_at; // 결제요청시간
    private String approved_at; //결제 승인 시간
    private String payload; //결제 승인 요청에 대해 저장값, 요청 시 전달내용

}
