package com.daalgae.daalgaeproject.login.model.dto;

public class MemberRoleDTO {

    private int memCode;
    private int authCode;
    private AuthorityDTO authority;

    public MemberRoleDTO() {
    }

    public MemberRoleDTO(int memCode, int authCode, AuthorityDTO authority) {
        this.memCode = memCode;
        this.authCode = authCode;
        this.authority = authority;
    }

    public int getMemCode() {
        return memCode;
    }

    public void setMemCode(int memCode) {
        this.memCode = memCode;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

    public AuthorityDTO getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityDTO authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "MemberRoleDTO{" +
                "memCode=" + memCode +
                ", authCode=" + authCode +
                ", authority=" + authority +
                '}';
    }
}
