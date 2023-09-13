package com.daalgae.daalgaeproject.login.model.dto;

import java.util.Date;
import java.util.List;

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

    public MemberDTO() {
    }

    public MemberDTO(int memCode, String memId, String memPwd, String memName, Date memBirth, String memEmail, String memAdrs, String memAdresDetail, Date memWithdrawal, int memDogGum, List<MemberRoleDTO> memberRoleList) {
        this.memCode = memCode;
        this.memId = memId;
        this.memPwd = memPwd;
        this.memName = memName;
        this.memBirth = memBirth;
        this.memEmail = memEmail;
        this.memAdrs = memAdrs;
        this.memAdresDetail = memAdresDetail;
        this.memWithdrawal = memWithdrawal;
        this.memDogGum = memDogGum;
        this.memberRoleList = memberRoleList;
    }

    public int getMemCode() {
        return memCode;
    }

    public void setMemCode(int memCode) {
        this.memCode = memCode;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemPwd() {
        return memPwd;
    }

    public void setMemPwd(String memPwd) {
        this.memPwd = memPwd;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public Date getMemBirth() {
        return memBirth;
    }

    public void setMemBirth(Date memBirth) {
        this.memBirth = memBirth;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public String getMemAdrs() {
        return memAdrs;
    }

    public void setMemAdrs(String memAdrs) {
        this.memAdrs = memAdrs;
    }

    public String getMemAdresDetail() {
        return memAdresDetail;
    }

    public void setMemAdresDetail(String memAdresDetail) {
        this.memAdresDetail = memAdresDetail;
    }

    public Date getMemWithdrawal() {
        return memWithdrawal;
    }

    public void setMemWithdrawal(Date memWithdrawal) {
        this.memWithdrawal = memWithdrawal;
    }

    public int getMemDogGum() {
        return memDogGum;
    }

    public void setMemDogGum(int memDogGum) {
        this.memDogGum = memDogGum;
    }

    public List<MemberRoleDTO> getMemberRoleList() {
        return memberRoleList;
    }

    public void setMemberRoleList(List<MemberRoleDTO> memberRoleList) {
        this.memberRoleList = memberRoleList;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memCode=" + memCode +
                ", memId='" + memId + '\'' +
                ", memPwd='" + memPwd + '\'' +
                ", memName='" + memName + '\'' +
                ", memBirth=" + memBirth +
                ", memEmail='" + memEmail + '\'' +
                ", memAdrs='" + memAdrs + '\'' +
                ", memAdresDetail='" + memAdresDetail + '\'' +
                ", memWithdrawal=" + memWithdrawal +
                ", memDogGum=" + memDogGum +
                ", memberRoleList=" + memberRoleList +
                '}';
    }
}