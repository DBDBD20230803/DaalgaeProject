package com.daalgae.daalgaeproject.payment.dto;


import lombok.*;

@Getter
@Setter
@ToString
@Data
public class KakaoApprove {

    private String aid;
    private String tid; // notnull. PK
    private String cid;
    private String partner_order_id;
    private String partner_user_id;  // 회원코드 FK mem_code??
    private String payment_method_type; // 결제 수단
    private Amount amount;  //결제금액
    private String item_name; //개껌 10 또는 개껌 20개
    private String item_code;
    private int quantity; // 수량
    private String created_at; // 결제일자
    private String approved_at;
    private String payload;

}
