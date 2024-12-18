package me.ahngeunsu.springbootdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    // 02 단계 - 이상 코드 작성 후 MemberRepositoryTest.java 파일로 이동
    // 해당 메서드는 상속받아 구현할 필요가 없기 때문에 @Override가 없음 메서드로 정의한 후
    // 가져다 쓰기만 하면 됨.
    
    /*
        5. 예제 코드 살펴보기 부분입니다.
        리포지토리는 엔티티에 있는 데이터들을 조회하거나 저장, 변경, 삭제를 할 때 사용하는 인터페이스로
        스프링 데이터 JPA에서 제공하는 인터페이스인 JpaRepository 클래스를 상속 받아 간단하게 구현 가능.
        
        JpaRepository 클래스를 상속 받을 때, 엔티티 Member와 엔티티 기본키 타입 Long을 인수로 넣어줌.
        이후 해당 리포지토리를 사용할 때 JpaRepository에서 제공하는 여러 메서드를 사용할 수 있게 됨.
        
        
        정리
        
        ORM은 객체와 데이터베이스를 연결하는 프로그래밍 기법
        JPA는 자바에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스
        하이버네이트는 JPA의 대표적인 구현체로, 자바 언어를 위한 ORM 프레임워크
        스프링 데이터 JPA는 JPA를 쓰기 편하게 만들어놓은 모듈
     */
}
