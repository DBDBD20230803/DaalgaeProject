package com.daalgae.daalgaeproject.payment.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoApproveResponse {

    private String aid;
    private String tid;
    private String cid;
    private String Partner_order_id;
    private String partner_user_id;
    private String patment_method_type;
    private Amount amount;
    private String item_name;
    private String item_code;
    private int quantity;
    private String created_at;
    private String approved_at;
    private String patload;

}
