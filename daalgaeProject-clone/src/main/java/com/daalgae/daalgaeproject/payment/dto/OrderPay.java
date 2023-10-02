package com.daalgae.daalgaeproject.payment.dto;


import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPay {

    private String dogGumPayTid; // 카카오에서 가져오는 고유 결제번호
    private String dogGumPayMethod; // 결제수단
    private String dogGumPay; // 결제 금액..
    private String dogGumPayDate; //결제일자
    private String dogItemName; // 개껌 10 또는 개껌300 개로 insert 됩니다.
    private int memDogGum; // 수량
    private int refMemCode; // 회원 코드
}
