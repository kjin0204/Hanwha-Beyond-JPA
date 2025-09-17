package com.orgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class EntityManagerCRUDTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager(){
        /* 설명. EntityManager가 생성될 때마다 고유의 새로운 영속성 컨텍스트(Entity 객체를 관리하는 창고)가 생성된다. */
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager(){
        entityManager.close();
    }

    @AfterAll
    public static void closeFactory(){
        entityManagerFactory.close();
    }

    @Test
    public void 메뉴코드로_메뉴_조회_테스트(){
        int menuCode = 2;

        /* 설명. entityManater를 통해 여러번 find를 해도 select은 한번만 동작한다.(feat. DB I/O 횟수 줄임) */
        Menu foundMenu = entityManager.find(Menu.class,  menuCode);
        Menu foundMenu2 = entityManager.find(Menu.class,  menuCode);
        
        /* 설명. 단중문은 두 개 이상 가능하며 동일성 보장 확인 */
        Assertions.assertNotNull(foundMenu);
        Assertions.assertEquals(foundMenu,foundMenu2); //jpa는 동일성을 보장한다.
        System.out.println("foundMenu = " + foundMenu);
    }

    @Test
    public void 새로운_메뉴_추가_테스트(){
        Menu menu = new Menu();
        menu.setMenuName("꿀발린 추어탕");
        menu.setMenuPrice(7000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(menu); //영속상태로 바꿈

        transaction.commit();
    }
}
