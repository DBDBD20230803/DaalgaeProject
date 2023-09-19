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
    private String memAdresDetail;
    private Date memWithdrawal;
    private int memDogGum;
    private List<MemberRoleDTO> memberRoleList;
    private String mailKey;


}