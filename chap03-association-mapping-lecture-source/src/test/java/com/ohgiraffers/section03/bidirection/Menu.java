package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.*;

@Entity(name="bidirection_menu")
@Table(name="tbl_menu")
public class Menu {
    @Id
    @Column(name="menu_code")
    private int menuCode;

    @Column(name="menu_name")
    private String menuName;

    @ManyToOne                       // 메뉴 -> 카테고리의 전체 카디널리티
    @JoinColumn(name="category_code")   // FK제약조건이 있는 컬럼(자식 테이블에 있는 컬럼명만 쓴다.)
    private Category category;          // 메뉴 1개가 카테고리 엔티티 객체 몇개를 가지는지(List<타입>/타입)

    @Column(name="menu_price")
    private int menuPrice;

    @Column(name="orderable_status")
    private String orderableStatus;

    public Menu() {
    }

    public Menu(int menuCode, String menuName, Category category, int menuPrice, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category cateogry) {
        this.category = cateogry;
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
//                ", cateogry=" + category +
                ", menuPrice=" + menuPrice +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
