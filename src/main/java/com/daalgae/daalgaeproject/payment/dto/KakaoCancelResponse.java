package com.daalgae.daalgaeproject.payment.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoCancelResponse {

    private String aid;
    private String tid;
    private String cid;
    private String status;
    private String partner_order_id;
    private String partner_user_id;
    private String payment_method_type;
    private Amount amount;
    private ApprovedCancelAmount approved_cancel_amount;// 이번 요청으로 취소된 금액
    private CanceledAmount canceled_amount; //누계 최소 금약
    private CancelAvailableAmount cancel_available_amount; //남은 취소 금액
    private String item_name;
    private String item_code;
    private int quantity;
    private String created_at;
    private String approved_at;
    private String canceled_at;
    private String patload;
}
