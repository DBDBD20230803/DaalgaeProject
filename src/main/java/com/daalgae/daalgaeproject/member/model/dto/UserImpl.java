
package com.daalgae.daalgaeproject.member.model.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


@Getter
@Setter
@ToString
public class UserImpl extends User {
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
    private int mailAuth;
    private String mailKey;
    private Date banPeriod;

    public UserImpl(String username, String password, boolean mailAuth, boolean memWithdrawal, boolean banPeriod, Collection<? extends GrantedAuthority> authorities) {
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
        this.memAdrsDetail = member.getMemAdrsDetail();
        this.memWithdrawal = member.getMemWithdrawal();
        this.memDogGum = member.getMemDogGum();
        this.memRole = member.getMemRole();
        this.mailAuth = member.getMailAuth();
        this.mailKey = member.getMailKey();
        this.banPeriod = member.getBanPeriod();
    }


    @Override
    public boolean isAccountNonExpired() {
        return memWithdrawal == null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return mailAuth == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        Date currentDate = new Date();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        if (banPeriod == null || banPeriod.compareTo(currentDate) < 0) {
            return true;
        } else {
            return false;
        }

    }
}
