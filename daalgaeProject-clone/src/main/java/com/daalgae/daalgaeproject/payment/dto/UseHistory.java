package com.daalgae.daalgaeproject.payment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UseHistory {

    private int dogGumUseCode; // 유저가 게시물을 읽은 고유 번호 ?
    private Date dogGumUseDate; // 결제 일자.
    private int dogGumUseAmount; // ?? 이건 사용한 개껌 수량
    private int refPostCode; // 웹툰 번호
    private int refMemCode; // 회원코드
}
