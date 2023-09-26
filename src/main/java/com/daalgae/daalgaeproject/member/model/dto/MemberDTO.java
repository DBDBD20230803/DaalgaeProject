package com.daalgae.daalgaeproject.member.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;



@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private int memCode;
    private String memId;
    private String memPwd;
    private String memName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memBirth;

    private String memEmail;
    private String memAdrs;
    private String memAdrsDetail;
    private Date memWithdrawal;
    private int memDogGum;
    private String memRole;
    private String memBanStatus;
    private int mailAuth;
    private String mailKey;

    public int isMailAuth() {
        return mailAuth;
    }
}