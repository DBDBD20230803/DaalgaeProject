package com.daalgae.daalgaeproject.payment.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApprovedCancelAmount {

    private int total; //이번 요청으로 취소된 전체 금액
    private int tax_free;// 이번 요청으로 취소된 비과세금액
    private int vat; // 이번 요청으로 취소된 부가세 금액
}
