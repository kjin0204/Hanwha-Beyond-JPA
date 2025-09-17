package com.ohgiraffers.section01.problem;

public class Menu {
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int cateogryCode;
    private String orderableStatus;

    public Menu() {
    }

    public Menu(int menuCode, String menuName, int menuPrice, int cateogryCode, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.cateogryCode = cateogryCode;
        this.orderableStatus = orderableStatus;
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

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getCateogryCode() {
        return cateogryCode;
    }

    public void setCateogryCode(int cateogryCode) {
        this.cateogryCode = cateogryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", cateogryCode=" + cateogryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
