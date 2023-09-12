package com.daalgae.daalgaeproject.payment.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Amount {

    private int total; //총 결제 금약
    private int tax_free;
    private int tax;
}
