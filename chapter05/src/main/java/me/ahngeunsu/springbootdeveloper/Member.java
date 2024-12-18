package me.ahngeunsu.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;    // DB 테이블의 'id' 컬럼과 매칭

    @Column(name = "name", nullable = false)
    private String name;    // DB 테이블의 'name' 컬럼과 매칭

    /*
        각 애너테이션의 역할은 lombok의 경우 java과정에서 설명했지만 추후 다시 설명 예정. 현재는 member라는 이름의 테이블에 접근하는 데
        사용할 객체 정도로 이해해주세요. 이후 member 테이블과 Member 클래스를 매핑하는 코드를 작성
     */

    public void changeName(String name) {
        this.name = name;
    }
    // 이후 다시 MemberRepositoryTest.java 파일로 넘어오세요.

    /*
        5. 예제 코드 살펴보기 들어가고 나서 보면 됩니다.
            1). @Entity 애너테이션 - Member 객체를 JPA가 관리하는 엔티티로 지정
                    Member 클래스와 실제 데이터베이스의 테이블을 매핑시킴.
                    @Entity의 곳ㄱ성 중에 name을 사용하면 name 값을 가진 테이블 이름과 매핑되고,
                    테이블 이름을 지정하지 않으면 클래스 이름과 같은 테이블과 매핑됨. 여기서는 테이블을 이름을 지정하지
                    않았으므로 클래스 이름과 같은 데이터베이스의 테이블인 member 테이블과 매핑됨.
                    @Entity 애너테이션에서 테이블을 지정하고 싶다면
                    @Entity(name = "member_list")와 같은 식으로 지정해줄 수 있음.

            2) protected 기본 생성자. 엔티티는 반드시 기본 생성자가 있어야 하고, 접근 제어자는 public 혹은
                protected여야 합니다. public보다는 protected가 더 안전하므로 접근 제어자가 protected인
                기본 생성자를 생성

            3) @Id는 Long 타입의 id 필드를 테이블의 기본키로 지정합니다.

            4) @GeneratedValue는 기본키의 생성 방식을 결정함. 여기서는 자동으로 기본키가 증가되도록 지정.

                * 자동키 생성 설정 방식
                    AUTO - 선택한 데이터베이스 방언에 따라 방식을 자동으로 선택(기본값)
                    IDENTITY - 기본키 생성을 데이터베이스에 위임(=AUTO_INCREMENT)
                    SEQUENCE - 데이터베이스 시퀀스를 사용해서 기본키를 할당하는 방법. 오라클에서 주로 사용
                    TABLE - 키 생성 테이블 사용

            5) @COLUMN 애너테이션은 데이터베이스의 컬럼과 필드를 매핑해줍니다.
                @COLUMN 애너테이션의 속성
                    name : 필드와 매핑할 컬럼 이름, 생성하지 않으면 필드 이름으로 지정해줌
                    nullable : 컬럼의 null 허용 여부, 설정하지 않으면 true(nullable)
                    unique : 컬럼의 유일한 값(unique) 여부, 설정하지 않으면 false(non-unique)
                    comlumnDefinition : 컬럼 정보 설정. default 값을 줄 수 있음.

        이후 MemberRepository.java로 넘어갑니다.
     */
}
