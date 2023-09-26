package com.daalgae.daalgaeproject.payment.dto;


import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPay {

    private String dogGumPayTid;
    private String dogGumPayMethod;
    private String dogGumPay; // Amount 소속에 있는 total 값
    private String dogGumPayDate;
    private String dogItemName; // 개껌 10 또는 개껌300 개로 insert 됩니다.
    private int memDogGum; // 수량;
    private int refMemCode;
}
