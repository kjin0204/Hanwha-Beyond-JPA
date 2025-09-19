package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
@Slf4j // logger를 롬복으로 대체
public class MenuController {
    private final MenuService menuService;

    /* 설명.
     *  Logger를 활용한 로그 생성
     *  1. println보다 성능적으로 우수하다.
     *  2. 외부 리소스 파일로 따로 저장 및 송출이 가능하다.
     *  3. 로그레벨에 따른 확인 가능하다.(개발: debug, 서비스: info.....)
    * */
//    Logger logger =  LoggerFactory.getLogger(MenuController.class);
//    Logger logger =  LoggerFactory.getLogger(getClass()); // 현재 클래스의 메타정보를 넣어 줌


    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 설명. url 경로 상에 넘어온 값을 변수에 바로 담을 수 있다.(PathVariable이라고 한다.) */
    @GetMapping("/{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode , Model model){
//        logger.info("menuCode = {} " , menuCode); Logger로 생성 했을때
        log.debug("menuCode = {}" , menuCode); //롬복 Slf4j 어노테이션 사용

        MenuDTO menu = menuService.findMenuByCode(menuCode);
        model.addAttribute("menu", menu);

        return "menu/detail";
    }

    /* 설명. 페이징 처리 전 */
    @GetMapping("/list")
    public String findMenuList(Model model){
        List<MenuDTO> mesuList = menuService.findMenuList();

        model.addAttribute("menuList", mesuList);

        return "menu/list";
    }


}
