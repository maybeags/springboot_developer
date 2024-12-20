package me.ahngeunsu.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
    1. 데이터베이스란?
        데이터를 매우 효율적으로 보관하고 꺼내볼 수 있는 곳.
        많은 사람들이 안전하게 데이터를 사용하고, 관리할 수 있음.

        데이터베이스 관리자 DBMS(Database Management System)
            데이터베이스를 관리하기 위한 소프트웨어를 의미함. 많은 사람이 공유할 수 있어야 하므로 동시 접근을 할 수 있어야 하는 등
            많은 요구사항이 존재함. DBMS는 이런 요구사항을 만족하면서도 효율적으로 데이터베이스를 관리하고 운영함.
            흔히 데이터베이스라고 알고 있는 MySQL, Oracle은 사실 DBMS에 해당함.
            DBMS는 관리 특징에 따라서 관계형, 객체-관계형, 도큐먼트형, 비관계형 등으로 분류함. 가장 많이 쓰이는 것은 관계형

            1) 관계형 DBMS
               Relational DBMS를 줄여 RDBMS로 부름.
               RDBMS는 테이블('표'를 의미) 형태로 이루어진 데이터 저장소를 뜻하는데, 예를 들어 회원 테이블이 있다고 가정했을 때,
               각 행은 고유의 키, 즉 아이디를 가지고 있고, 이메일, 나이와 같은 회원과 관련된 값들이 들어갑니다.

               회원 테이블
                1열           2열           3열
               +----------------------------------+
               | ID         | 이메일       | 나이  | - header / column
               ------------------------------------
               | 1          | a@test.com  | 10    | - 1행
               | 2          | b@test.com  | 20    | - 2행
               | 3          | c@test.com  | 30    | - 3행
               +----------------------------------+
                기본키(PK)

           2) H2, MySQL
                해당 수업에서 사용할 RDBMS는 H2, MySQL
                H2 - 자바로 작성되어 있는 RDBMS : 스프링 부트가 지원하는 인메모리 관계형 데이터베이스
                        데이터를 다른 공간에 따로 보관하는 것이 아니라 애플리케이션 자체 내부에 데이터를 저장하는 특정이 있음.
                        그래서 애플리케이션 재실행시 데이터는 초기화됨. 간편하게 사용하기 좋아서 개발 시 테스트 용도로 많이 사용됨.
                        실제 서비스에서는 사용하지 않음.
                MySQL - 실제 서비스로 올릴 때 사용할 RDBMS - 추후 수업 예정

          꼭 알아야 할 용어
            1) 테이블 : 데이터베이스에서 데이터를 구성하기 위한 가장 기본적인 단위. 테이블은 행과 열로 구성되며 행은 여러 속성으로 구성
            2) 행(row) : 테이블의 구성 요소 중 하나이며 테이블의 가로로 배열된 데이터의 집합을 의미.
                행은 반드시 고유한 식별자인 기본키를 가짐. 행을 레코드(record)로 부르기도 함.
            3) 열(column) : 테이블의 구성 요소 중 하나이며, 행에 저장되는 유형의 데이터. 예를 들어 회원 테이블이 있다고 할 때,
                열은 각 요소에 대한 속성을 나타내며 무결성을 보장함. 이상의 경우 이메일은 문자열, 나이는 숫자 유형을 가짐.
                이메일 열에 숫자가 들어가거나, 나이 열에 문자열이 들어갈 수 없기 때문에 데이터에 대한 무결성을 보장함.
            4) 기본키(primary key) : 행을 구분할 수 있는 식별자. 이 값은 테이블에서 유일해야 하며 중복값을 가질 수 없음.
                보통 데이터를 수정하거나 삭제하고, 조회할 때 사용되며 다른 테이블과 관계를 맺어 데이터를 가져올 수도 있음.
                또한 기본키의 값은 수정되어서는 안 되며 유효한 값이어야 함. 즉, NULL이 될 수 없음. -> 이전 수업에서 nullable을 확인함
            5) 쿼리(query) : 데이터베이스에서 데이터를 조회하거나 삭제, 생성, 수정 같은 처리를 하기 위해 사용하는 명령문.
                SQL이라는 데이터베이스 전용 언어를 사용하여 작성함.

                - mySQL이용해서 쿼리 배워야 할듯

    2. ORM이란?
        Object-relational Mapping은 자바의 객체와 데이터베이스를 연결하는 프로그래밍 기법.
        예를 들어 데이터베이스에 age, name 컬럼에 20, 홍길동이라는 값이 들어있다고 가정했을 때, 이 것을 자바에서 사용하려면 SQL을
        이용하여 데이터를 꺼내 사용하지만, ORM이 있다면 데이터베이스의 값을 마치 객체처럼 사용할 수 있음.
        즉, SQL에 어려움을 겪는다고 하더라도 자바 언어로만 데이터베이스에 접근해서 원하는 데이터를 받아올 수 있는 방식.
        즉, 객체와 데이터베이스를 연결해 자바 언어로만 데이터베이스를 다룰 수 있게 하는 도구를 ORM이라고 함.

        장점 :
            1) SQL을 직접 작성하지 않고 사용하는 언어로 데이터베이스에 접근할 수 있음.
            2) 객체지향적으로 코드를 작성할 수 있기 때문에 비지니스 로직에만 집중할 수 있음.
            3) 데이터베이스 시스템이 추상화되어 있기 때문에 MySQL에서 PostgreSQL로 전환한다고 해도 추가로 드는 작업이 거의 없음.
                즉, 데이터베이스 시스템에 대한 종속성이 줄어듦.
            4) 매핑하는 정보가 명확하기 떄문에 ERD에 대한 의존도를 낮출 수 있고 유지보수할 때 유리.
        단점 :
            1) 프로젝트의 복잡성이 커질수록 사용 난이도도 올라감.
            2) 복잡하고 무거운 쿼리는 ORM으로 해결이 불가능한 경우가 있음.

    3. JPA와 하이버네이트란?
        DBMS에 여러 종류가 있는 것처럼 ORM에도 여러 종류가 있음. 자바에서는 JPA(Java Persistence API)를 표준으로 사용.
        JPA : 자바에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스.
        인터페이스이므로 실제 사용을 위해서는 ORM 프레임워크를 추가로 선택해야 함. 대표적으로 하이버네이트(Hibernate).
        하이버네이트 : JPA 인터페이스를 구현한 구현체이자 자바용 ORM 프레임워크. 내부적으로는 JDBC API 이용.
            하이버네이트의 목표는 자바 객체를 통해 데이터베이스 종류에 상관없이 데이터베이스를 자유자재로 사용할 수 있게 하는 데 있음.

        JPA와 하이버네이트의 역할
            JPA : 자바 객체와 데이터베이스를 연결해 데이터를 관리. 객체 지향 도메인 모델과 데이터베이스의 다리 역할.
            하이버네이트 : JPA의 인터페이스를 구현. 내부적으로 JDBC API 이용.

        엔티티 매니저란?
            엔티티(Entity) : 데이터베이스의 테이블과 매핑되는 객체를 의미. 본질적으로 자바 객체이므로 일반 객체와 다르지 않지만,
                데이터베이스의 테이블과 직접 연결된다는 특징이 있어 구분지어 부르는 편. -> 데이터베이스에 영향을 미치는 쿼리를 실행하는 객체

            엔티티 매니저(Entity Manager) : 엔티티를 관리해 데이터베이스와 애플리케이션 사이에서 객체를 생성, 수정, 삭제하는 등의
                역할을 함. 이러한 엔티티 매니저를 만드는 곳이 엔티티 매니저 팩토리(Entity Manager Factory)
                예를 들어 회원 2 명이 동시에 회원 가입을 하려는 경우 엔티티 매니저는
                    1) 회원 1의 요청에 대해서 가입 처리를 할 엔티티 매니저1를 엔티티 매니저 팩토리가 생성하면 이를 통해 가입 처리해
                        데이터베이스에 회원 정보를 저장
                    2) 회원 2의 요청에 대해서 가입 처리를 할 엔티티 매니저2를 엔티티 매니저 팩토리가 생성하면 이를 통해 가입 처리해
                        데이터베이스에 회원 정보를 저장

                하지만 스프링 부트에서는 직접 엔티티 매니저 팩토리를 만들어서 관리하지는 않고 스프링 부트 내부에서
                    엔티티 매니저 팩토리를 하나만 생성해서 관리하고 @PersistenceContext 또는 @Autowired 애너테이션을 이용해
                    엔티티 매니저를 사용함.

                    ↓ 스프링 부트가 엔티티 매니저를 사용하는 방법 예
                    @PersistenceContext
                    EntityManager em;   // 프록시 엔티티 매니저. 필요할 때 진짜 엔티티 매니저 호출

                스프링 부트는 기본적으로 빈을 하나만 생성해서 공유하므로 동시성 문제가 발생할 수 있음.
                그래서 실제로는 엔티티 매니저가 아닌 실제 엔티티 매니저와 연결하는 프록시(가짜) 엔티티 매니저를 사용함.
                필요할 때만 데이터베이스 트랜잭션과 관련된 실제 엔티티 매니저를 호출하는 것.

                * 쉽게 말해 엔티티 매니저는 SpringData JPA에서 관리하므로 직접 생성하거나 관리할 필요가 없다.

        영속성 컨텍스트란?
            엔티티 매니저는 엔티티를 영속성 컨텍스트에 저장한다는 특징을 지니고 있음.
            영속성 컨텍스트 : JPA의 중요한 특징 중 하나로, 엔티티를 관리하는 가상의 공간을 의미하며, 일르 통해 데이터베이스에서
                효과적으로 데이터를 가져올 수 있고, 엔티티를 편하게 사용할 수 있음.


            1차 캐시
                영속성 컨텍스트는 내부에 1차 캐시를 가지고 있음. 이 때 캐시의 키는 엔티티의 @Id 애너테이션이 달린 기본키 역할을 하는 식별자이며 값은 엔티티.
                엔티티를 조회하면 1차 캐시에서 데이터를 조회하고 값이 있으면 반환됨. 값이 없으면 데이터베이스에서 조회해 1차 캐시에 저장한 다음 반환함.
                이를 통해 캐시된 데이터를 조회할 때에는 데이터베이스를 거치지 않아도 되므로 빠르게 데이터를 조회 가능.

            쓰기 지연
                쓰기 지연(transactional write-behind)은 트랙잭션을 커밋하기 전까지는 데이터베이스에 실제로 질의문을 보내지 않고 쿼리를 모았다가
                트랜잭션을 커밋하면 모았던 쿼리를 한번에 실행하는 것을 의미함. 예를 들어 데이터 추가 쿼리가 3개라면 영속성 컨텍스트는 트랜잭션을 커밋하는
                시점에 3개의 쿼리를 한꺼번에 전송함. 이를 통해 적당한 묶음으로 쿼리를 요청할 수 있어 데이터베이스 시스템의 부담을 줄일 수 있음.

            변경 감지
                트랜잭션을 커밋하면 1차 캐시에 저장되어 있는 엔티티의 값과 현재 엔티티의 값을 비교해서 변경된 값이 있다면 변경 사항을 감지해 변경된 값을
                데이터베이스에 자동으로 반영함. 이를 통해 쓰기 지연과 마찬가지로 적당한 묶음으로 쿼리를 요청할 수 있고, 데이터베이스 시스템의 부담을 줄일
                수 있음

            지연 로딩
                지연 로딩(lazy loading)은 쿼리로 요청한 데이터를 애플리케이션에 바로 로딩하는 것이 아니라 필요할 때 쿼리를 날려 데이터를 조회함.
                    -> 조회할 떄 쿼리를 보내 연관된 모든 데이터를 가져오는 즉시 로딩도 존재함.

            해당 특징들이 갖는 공통점은 데이터베이스 접근을 최소화해 성능을 높일 수 있다는 점. 캐시를 하거나, 자주 쓰지 않게 하거나, 변화를 자동 감지해서
            미리 준비하거나 하는 등의 방법을 통해서.

        엔티티의 상태
            엔티티는 4 가지 상태를 가짐.
                1) 분리(detached) 상태 : 영속성 컨텍스트가 관리하고 있지 않는 상태
                2) 관리(managed) 상태 : 영속성 컨텍스트가 관리하는 상태
                3) 비영속(transient) 상태 : 영속성 컨텍스트와 전혀 관계가 없는 상태
                4) 삭제된(removed) 상태 : 삭제된 상태 그 자체

            엔티티 상태 변경의 예 -> 실습하지 않아도 됩니다. 코드만 예시로 보여드릴거라 주석 처리된 상태로 작성합니다.

public class EntityManagerTest {

    @Autowired
    EntityManager em;

    public void example() {
    // 1.엔티티 매니저가 엔티티를 관리하지 않는 상태(비영속 상태)
    Member member = new Member(1L, "홍길동");

    // 2. 엔티티가 관리되는 상태
    em.persist(member);
    // 3. 엔티티 객체가 분리된 상태
    em.detach(member);
    // 4. 엔티티 객체가 삭제된 상태
    em.remove(member);
    }
}

    4. 스프링 데이터와 스프링 데이터 JPA
        스프링 데이터(spring data) - 비지니스 로직에 더 집중할 수 있게 데이터베이스 사용 기능을 클래스 레벨에서 추상화함
            스프링 데이터에서 제공하는 인터페이스를 통해서 스프링 데이터 사용 가능
            CRUD를 포함한 여러 메서드가 포함되어, 자동으로 쿼리 생성 가능. 또한 페이징 처리 기능과 메서드 이름으로
            자동으로 쿼리를 빌딩하는 기능 등이 있음 -> SQL문을 학습해두면 더 좋겠죠
            각 데이터베이스의 특성에 맞춰 기능을 확장해 제공하는 기술도 제공함.

            예를 들어 표준 스펙에 해당하는 JPA는 스프링에서 구현한 스프링 데이터 JPA를, 몽고DB는 스프링 데이터 몽고DB를 사용하는 등

        스프링 데이터 JPA란?
            스프링 데이터의 공통적인 기능에서 JPA의 유용한 기술이 추가된 기술.
            스프링 데이터 JPA에서는 스프링 데이터의 인터페이스인 PagingAndSortingRepository를 상속받아
            JpaRepository 인터페이스를 만들었으며, JPA를 더 편리하게 사용하는 메서들르 제공함.

            ⬇️ 메서드 호출로 엔티티 상태 변경 예
    @PErsistenceContext
    EntityManager em;

    public void join() {
    // 기존에 엔티티 상태를 바꾸는 방법(메서드를 호출해서 상태 변경)
    Member member = new Member(1L, "홍길동");
    em.persist(member);
    }
            하지만 스프링 데이터 JPA를 사용하면 리포지토리 역할을 하는 인터페이스를 만들어 데이터베이스의 테이블 조회, 수정, 생성, 삭제 같은 작업을
            간단히 할 수 있음. 다음과 같이 JpaRepository 인터페이스를 우리가 만든 인터페이스에서 상속받고, 제네릭에는 관리할
            <엔티티 이름, 엔티티 기본키의 타입>을 입력하면 기본 CRUD 메서드를 사용할 수 있음

            ⬇️ 기본 CRUD 메서드를 사용하기 위한 JpaRepository 상속 예
            public interface MemberRepository extends JpaRepository<Member, Long> {}


        스프링 데티어 JPA에서 제공하는 메서드 사용해보기

            01 단계 - MemberRepository.java파일로 이동해서 create test를 누르고 테스트 파일 기본값을 그대로 두고 ok를 눌러서 만들 것





 */
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}

