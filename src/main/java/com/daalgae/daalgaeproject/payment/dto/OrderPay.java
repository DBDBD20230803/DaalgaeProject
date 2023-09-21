package com.daalgae.daalgaeproject.payment.dto;


import com.daalgae.daalgaeproject.member.model.dto.MemberDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPay {

    private String dogGumPayCode;
    private int memCode;
    private String dogGumPaymethod;
    private String dogGumPay; // Amount 소속에 있는 total 값
    private String dogGumPayDate;
    private int memDogGum; // 수량;
    private int dogGumUseCode; //개껌 10개 또는 20개 인 컬럼
    private String dogItemName;
    private List<MemberDTO> member;

}
