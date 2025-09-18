package com.ohgiraffers.section04.enumtype;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class EnumTypeMappingTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        /* 설명. EntityManager가 생성될 때마다 고유의 새로운 영속성 컨텍스트(Entity 객체를 관리하는 창고)가 생성된다. */
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @Test
    public void enum타입_매핑_테스트(){
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");
        member.setEmail("hong@gmail.com");
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new java.util.Date());
        member.setMemberRole(RoleType.ROLE_MEMBER);
        member.setStatus("Y");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(member);


        /* 설명. persist 당시에는 부여되지 않은 pk값으로 commit 이후 조회를 하면 가능할까? */
        Member selectedMember = entityManager.find(Member.class, 1);
        System.out.println("selectedMember = " + selectedMember);
        System.out.println("member = " + member);

        transaction.commit();
    }
}
