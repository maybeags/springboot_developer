package me.ahngeunsu.springbootdeveloper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
/*
    학습 테스트 예정
        학습 테스트는 기능 구현을 위한 테스트라기보다는 우리가 사용하는 라이브러리, 프레임워크에서 지원하는 기능을 검증하며 어떻게 동작하는지 파악하는 테스트

        조회 메서드 사용해보기
                member 테이블에 있는 모든 데이터를 가져오려면
                SELECT * FROM member
                쿼리를 작성했어야했지만, JPA에서 데이터를 가져올 때는 findAll()메서드를 사용 가능함.

                01 단계 - 데이터 조회를 위해 테스트용 데이터 추가하기
                    test-resources에 마우스 우클릭하여 insert-members.sql파일 생성
                02 단계 - application.yml파일 생성 - src/main/resources 폴더 내에 있는 data.sql 파일을 자동으로 실행하지 않게 하는 옵션
                03 단계 - 해당 파일에 테스트 코드 작성
 */
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Sql("/insert-members.sql")
    @Test
    void getAllMembers() {
        //when
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members.size()).isEqualTo(3);
    }
    /*
        @Sql 애너테이션 : 테스트를 실행하기 전에 SQL 스크립트를 실행시킬 수 있음

        04 단계 - 테스트 메서드 실행
        05 단계 - 디버그 모드를 사용해 객체 확인
        06 단계 -
            1) 검증문을 작성한 줄의 번호를 눌러 빨간색 원 표시를 하기 -> 이거 기준으로는 38번 라인 - 중단점
            2) 메서드 실행문 왼쪽에 있ㄴㄴ 실행 버튼을 누른 다음 벌레 모양 아이콘을 눌러 디버그 모드 실행
            3) 그 코드가 실행되는 동안 어떤 값들을 지니고 있는지 확인할 수 있고, SQL문으로 추가한 데이터인 ABC 멤버의 정보 확인 가능
        07 단계 - 다시 조회로 돌아와서 id로 멤버를 찾는 방법. id가 2인 멤버를 찾고 싶으면
            SELECT * FROM member WHERE id = 2;
            로 쿼리 작성을 하면 됨
            하지만 JPA에서는
     */
    @Sql("/insert-members.sql")
    @Test
    void getMemberById() {
        // when
        Member member = memberRepository.findById(2L).get();

        // then
        assertThat(member.getName()).isEqualTo("B");
    }
    /*
        와 같이 작성할 수 있음.

    쿼리 메서드 사용해보기
        그런데 만약 id가 아닌 name으로 찾고 싶을 때는 어떡해야 할까?
        id는 모든 테이블에서 기본키로 사용하므로 값이 없거나 하지 않음(not nullable). 하지만 name의 경우 nullable이기 때문에 JPA에서 기본으로
        name을 찾아주는 메서드를 지원하지 않음. 하지만 메서드 이름으로 쿼리를 작성하는 기능을 제공함.

        01 단계 -
     */
}