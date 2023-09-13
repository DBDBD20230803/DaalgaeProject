package com.daalgae.daalgaeproject.login.model.dto;

public class GlobalMenuDTO {
    private int menuCode;
    private String menuName;
    private String menuUrl;
    private int type;
    private Integer refMenuCode;

    public GlobalMenuDTO() {
    }

    public GlobalMenuDTO(int menuCode, String menuName, String menuUrl, int type, Integer refMenuCode) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.type = type;
        this.refMenuCode = refMenuCode;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getRefMenuCode() {
        return refMenuCode;
    }

    public void setRefMenuCode(Integer refMenuCode) {
        this.refMenuCode = refMenuCode;
    }

    @Override
    public String toString() {
        return "GlobalMenuDTO{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", type=" + type +
                ", refMenuCode=" + refMenuCode +
                '}';
    }
}
