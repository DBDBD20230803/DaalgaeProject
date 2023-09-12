package com.daalgae.daalgaeproject.payment.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CanceledAmount {

    private int total; //취소된 전체 누적금액
    private int tax_free;
    private int vat;
}
