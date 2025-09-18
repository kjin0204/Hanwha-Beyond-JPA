package com.ohgiraffers.section02.onetomany;

import com.ohgiraffers.section01.manytoone.Category;
import jakarta.persistence.*;


@Entity(name="menu_section02")
@Table(name="tbl_menu")
public class Menu {
    @Id
    @Column(name="menu_code")
    private int menuCode;

    @Column(name="menu_name")
    private String menuName;

    @Column(name="category_code")
    private int categoryCode;

    @Column(name="menu_price")
    private int menuPrice;

    @Column(name="orderable_status")
    private String orderableStatus;

    public Menu() {
    }

    public Menu(int menuCode, String menuName, int categoryCode, int menuPrice, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.categoryCode = categoryCode;
        this.menuPrice = menuPrice;
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

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
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
                ", categoryCode=" + categoryCode +
                ", menuPrice=" + menuPrice +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
