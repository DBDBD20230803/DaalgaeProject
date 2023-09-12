package com.daalgae.daalgaeproject.payment.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CancelAvailableAmount {

    private int total;
    private int tax_free;
    private int vat;
}
