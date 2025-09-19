package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuSerice;

    /* 설명.
     *  Logger를 활용한 로그 생성
     *
    * */
    Logger logger =  LoggerFactory.getLogger(MenuController.class);

    @Autowired
    public MenuController(MenuService menuSerice) {
        this.menuSerice = menuSerice;
    }

    /* 설명. url 경로 상에 넘어온 값을 변수에 바로 담을 수 있다.(PathVariable이라고 한다.) */
    @GetMapping("/{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode , Model model){
        System.out.println("menuCode = " + menuCode);
        logger.info("menuCode = {} " , menuCode);

        return null;
    }
}
