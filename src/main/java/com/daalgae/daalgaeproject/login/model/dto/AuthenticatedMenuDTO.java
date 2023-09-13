package com.daalgae.daalgaeproject.login.model.dto;

public class AuthenticatedMenuDTO {
    private int authCode;
    private int menuCode;
    private GlobalMenuDTO globalMenu;

    public AuthenticatedMenuDTO() {
    }

    public AuthenticatedMenuDTO(int authCode, int menuCode, GlobalMenuDTO globalMenu) {
        this.authCode = authCode;
        this.menuCode = menuCode;
        this.globalMenu = globalMenu;
    }

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public GlobalMenuDTO getGlobalMenu() {
        return globalMenu;
    }

    public void setGlobalMenu(GlobalMenuDTO globalMenu) {
        this.globalMenu = globalMenu;
    }

    @Override
    public String toString() {
        return "AuthenticatedMenuDTO{" +
                "authCode=" + authCode +
                ", menuCode=" + menuCode +
                ", globalMenu=" + globalMenu +
                '}';
    }
}
