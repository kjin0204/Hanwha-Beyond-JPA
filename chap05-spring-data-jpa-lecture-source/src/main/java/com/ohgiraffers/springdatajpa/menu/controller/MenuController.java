package com.ohgiraffers.springdatajpa.menu.controller;

import com.ohgiraffers.springdatajpa.common.PagingButtonInfo;
import com.ohgiraffers.springdatajpa.common.Pagination;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String findMenuByCode(@PathVariable int menuCode, Model model) {
//        logger.info("menuCode = {} " , menuCode); Logger로 생성 했을때
        log.debug("menuCode = {}", menuCode); //롬복 Slf4j 어노테이션 사용

        MenuDTO menu = menuService.findMenuByCode(menuCode);
        model.addAttribute("menu", menu);

        return "menu/detail";
    }

    /* 설명. 페이징 처리 전 */
//    @GetMapping("/list")
//    public String findMenuList(Model model){
//        List<MenuDTO> mesuList = menuService.findMenuList();
//
//        model.addAttribute("menuList", mesuList);
//
//        return "menu/list";
//    }

    /* 설명.
     *  @PageableDefault
     *  1. 기본 한 페이지에 10개의 데이터(size, value)
     *  2. 기본 1페이지부터(0부터)
     *  3. 기본 오름차순(ASC)
     *
     *  설명.
     *   쿼리스트링을 쓰는 경우?
     *   1. 서버로 현재 페이지 정보 전달
     *   2. 서버로 정렬 기준 전달
     *   3. 서버로 검색어 전달
     * */

    /* 설명. 페이징 처리 후 */
    @GetMapping("/list")
    public String findMenuList(@PageableDefault(size = 15) Pageable pageable, Model model) {
        log.debug("pageable : {}", pageable);
        Page<MenuDTO> menuList = menuService.findMenuList(pageable);

        log.debug("조회한 내용 목록: {}", menuList.getContent());
        log.debug("총 페이지 수: {}", menuList.getTotalPages());
        log.debug("총 메뉴 수: {}", menuList.getTotalElements());
        log.debug("해당 페이지에 표시 될 요소 수: {}", menuList.getSize());
        log.debug("해당 페이지에 실제 요소 수: {} ", menuList.getNumberOfElements());
        log.debug("Page의 number가 처음이면(첫 페이지면): {}", menuList.isFirst());
        log.debug("Page의 number가 마지막이면(마지막 페이지면): {}", menuList.isLast());
        log.debug("현재 페이지: {}", menuList.getNumber());
        log.debug("정렬 기준: {}", menuList.getSort());

        /* 설명. Page객체를 통해 pagingButtonInfo(front가 페이징 처리 버튼을 그리기 위한 재료를 지닌) 추출 */
        PagingButtonInfo paging = Pagination.getPaginButtonInfo(menuList);


        model.addAttribute("menuList", menuList);
        model.addAttribute("paging", paging);

        return "menu/list";
    }

    @GetMapping("/regist")
    public void registMenu() {
    }

    @GetMapping("/category")
    @ResponseBody   // 핸드러 메소드의 반환형이 View Resolver를 무시해야 할 때(feat. json 문자열로 변환)
    public List<CategoryDTO> findCategoryList() {
        List<CategoryDTO> catgories = menuService.findAllCateogry();
        log.debug("뷰로 넘기기전에 카테고리 목록 확인 : {}",catgories);
        return catgories;
    }

    @PostMapping("/regist")
    public String registMenu(MenuDTO newMenu) {
        menuService.registMenu(newMenu);
        return "redirect:/menu/list";
    }

    @GetMapping("/modify")
    public void modifyMenuPage(){}

    @PostMapping("/modify")
    public String modifyMenu(MenuDTO modifyMenu){
        menuService.modifyMenu(modifyMenu);
        System.out.println("modifyMenu = " + modifyMenu);
        return "redirect:/menu/" + modifyMenu.getMenuCode();
    }

    @GetMapping("/delete")
    public void deleteMenuPage(){}

    @PostMapping("/delete")
    public String deleteMenu(@RequestParam int menuCode){
        menuService.deleteMenu(menuCode);

        return "redirect:/menu/list";
    }

    @GetMapping("/querymethod")
    public void querymethodPage(){}

    @GetMapping("/search")
    public String findMenuPrice(@RequestParam int menuPrice ,Model model){
        List<MenuDTO> menuList = menuService.findMenuPrice(menuPrice);

        model.addAttribute("menuList", menuList);
        model.addAttribute("menuPrice", menuPrice);

        return "menu/searchResult";
    }

}
