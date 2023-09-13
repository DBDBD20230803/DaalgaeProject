package com.daalgae.daalgaeproject.login.model.dto;

import java.util.List;

public class AuthorityDTO {

    private int authCode;
    private String authName;
    private List<AuthenticatedMenuDTO> authenticatedMenuDTOList;

    public AuthorityDTO() {
    }

    public AuthorityDTO(int authCode, String authName, List<AuthenticatedMenuDTO> authenticatedMenuDTOList) {
        this.authCode = authCode;
        this.authName = authName;
        this.authenticatedMenuDTOList = authenticatedMenuDTOList;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public List<AuthenticatedMenuDTO> getAuthenticatedMenuDTOList() {
        return authenticatedMenuDTOList;
    }

    public void setAuthenticatedMenuDTOList(List<AuthenticatedMenuDTO> authenticatedMenuDTOList) {
        this.authenticatedMenuDTOList = authenticatedMenuDTOList;
    }

    @Override
    public String toString() {
        return "AuthorityDTO{" +
                "authCode=" + authCode +
                ", authName='" + authName + '\'' +
                ", authenticatedMenuDTOList=" + authenticatedMenuDTOList +
                '}';
    }
}
