
package com.daalgae.daalgaeproject.member.model.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;



@Getter
@Setter
@ToString
public class UserImpl extends User{
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


    public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }


    public void setDetails(MemberDTO member) {
        this.memCode = member.getMemCode();
        this.memId = member.getMemId();
        this.memPwd = member.getMemPwd();
        this.memName = member.getMemName();
        this.memBirth = member.getMemBirth();
        this.memEmail = member.getMemEmail();
        this.memAdrs = member.getMemAdrs();
        this.memAdresDetail = member.getMemAdresDetail();
        this.memWithdrawal = member.getMemWithdrawal();
        this.memDogGum = member.getMemDogGum();
        //this.memberRoleList = member.getMemberRoleList();
    }
}

