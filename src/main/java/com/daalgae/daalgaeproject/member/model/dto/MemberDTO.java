package com.daalgae.daalgaeproject.member.model.dto;

import lombok.*;

import java.util.Date;
import java.util.List;


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