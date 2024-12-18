package me.ahngeunsu.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
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

        01 단계 - 예를 들어 name의 값이 'C'인 멤버를 찾아야 하는 경우 SQL
            SLECT * FROM member WHERE name = 'C';

            이런 쿼리를 동적 메서드로 만들 경우 -> MemberRepository.java로 이동 -> src/main임
     */

    @Sql("/insert-members.sql")
    @Test
    void getMemberByName() {
        // when
        Member member = memberRepository.findByName("C").get();

        // then
        assertThat(member.getId()).isEqualTo(3);
    }
    /*
        테스트 후 확인
        해당 기능을 쿼리 메서드라고 함.
        쿼리 메서드 : JPA가 정해준 메서드 이름 규칙을 따르면 쿼리문으로 작성하지 않아도 메서드처럼
            사용이 가능함. 추후 수업 예정

        현재까지의 수업 내용 예시는
            전체 조회 -> findAll()
            id로 조회 -> findById()
            특정 컬럼으로 조회 -> 쿼리 메서드 규칙에 맞게 정의 후 사용(여기서는 name 컬럼)

        추가, 삭제 메서드 사용해보기
            예를 들어 새로운 1번 멤버 'A'를 추가하려면 다음과 같은 쿼리문이 필요함
            INSERT INTO member (id, name) VALUES (1, 'A');

            JPA에서는 이 쿼리를 직접 입력하는 대신 save()라는 메서드를 사용함.

                01 단계 - MemberRepositoryTest.java 파일에 작성
     */
    @Test
    void saveMember() {
        // given
        Member member = new Member(1L, "A");

        // when
        memberRepository.save(member);

        //then
        assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("A");
    }

    /*
            02 단계 - 만약 엔티티를 한꺼번에 젖아하고 싶다면 saveAll() 메서드를 사용할 수 있음.
                다음 코드도 작성하고 실행 예정.
                추가할 멤버 객체들을 리스트로 만들고 saveAll() 메서드로 한꺼번에 추가한 후,
                추가한 멤버 객체 수만큼 데이터에 들어있는지 확인하는 테스트.
     */
    @Test
    void saveMembers() {
        // given
        List<Member> members = List.of(new Member(2L, "B"),
                new Member(3L, "C"));

        // when
        memberRepository.saveAll(members);

        // then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    /*
        03 단계 - 멤버 삭제 // 쿼리문의 경우 DELELTE문을 사용함. 예를 들어 member 테이블에 있는
            id가 2인 멤버를 삭제할 때는
            DELETE FROM member WHERE id = 2;

            JPA에서는 deleteById()를 사용하면 아이디로 레코드를 삭제할 수 있음. 여기서는 미리
            스크립트로 작성한 insert-members.sql을 실행하여 3명의 멤버를 추가하고 deleteById()
            메서드를 사용해 2번 멤버를 삭제한 뒤 2번 아이디를 가진 레코드가 있는지 조회할 예정.
            삭제된 데이터이므로 isEmpty() 결과값이 true인지 확인 해야 함.
     */
    @Sql("/insert-members.sql")
    @Test
    void deleteMemberById() {
        // when
        memberRepository.deleteById(2L);

        // then
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    /*
        04 단계 - 만약 실제 값이 그런지 눈으로 확인하기 위해서는 디버깅 모드를 사용해야 합니다.
            1) 검증문에 브레이크 포인트를 잡고, deleteMemberById() 메서드를 디버그 모드로 실행
            2) 디버그 창의 구문 실행 바를 이용해 memberRepository.findAll()을 입력하고 enter를
                누르면 result 라는 결과가 나타납니다.
            3) result를 펼쳐서 결과를 확인해보면 B를 제외한 A, C만 남은 것을 확인할 수 있습니다.

        05 단계 - 만약 모든 데이터를 삭제하고 싶다면 deleteAll() 메서드를 이용할 수 있음.
            해당 메서드를 쿼리로 변형하면
            DELETE FROM member
     */

    @Sql("/insert-members.sql")
    @Test
    void deleteAll() {
        // when
        memberRepository.deleteAll();

        // then
        assertThat(memberRepository.findAll().size()).isZero();
    }
    /*
        하지만 이 메서드는 정말 모든 데이터를 삭제하므로 실제 서비스 코드에서는 거의 사용하지 않음
        사용 예시 -> 테스트 간의 결리를 보장하기 위해
            즉, 한 테스트의 실행으로 데이터베이스가 변경되었을 때 다른 테스트가 그 데이터베이스를
                사용할 때 영향을 주지 않도록 하기 위함.
        06 단계 - 그래서 보통은 @AfterEach 애너테이션을 붙여 cleanUp() 메서드와 같은 형태로 사용함.
     */

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }
    // 이상의 형태로 자주 사용함.

    /*
        레코드 추가 -> save()
        한꺼번에 여러 레코드 추가 -> saveAll()
        아이디로 레코드 삭제 -> deleteById()
        모든 레코드 삭제 -> deleteAll()

        수정 메서드 사용해보기
            JPA로 데이터를 수정하는 방법
            예를 들어 id가 2인 멤버의 이름을 "BC"로 바꾸려면 쿼리문으로는
            UPDATE member
            SET name = 'BC'
            WHERE id = 2;

            하지만 JPA에서 데이터를 수정할 때는 다른 방법을 사용함. 왜냐하면 JPA는 트랜잭션 내에서
            데이터를 수정해야 하기 때문. 따라서 데이터를 수정할 때는 그냥 메서드만 사용해서는 안되고
            '@Transactional' 애너테이션을 메서드에 추가해야 함. 해당 방법에 대해서 수업합니다.

            01 단계 - Member.java 파일에 메서드를 추가할겁니다. 해당 메서드는 name의 필드 값을 바꾸는
                단순한 메서드입니다.

                changeName() 메서드를 추가한 후,

                해당 메서드가 @Transactional 애너테이션이 포함된 메서드에서 호출되면
                    JPA는 변경 감지(dirty checking) 기능을 통해 엔티티의 필드값이 변경될 때 그 변경 사항을
                    데이터베이스에 자동으로 반영합니다. 만약 엔티티가 영속 상태일 때 필드값을 변경하고
                    트랜잭션이 커밋되면 JPA는 변경사항을 데이터베이스에 자동으로 적용합니다.

            02 단계 - 실행 확인 전에 MemberRepositoryTest.java에 해당 코드를 추가합니다.
                  insert-members.sql 스크립트로 3명의 멤버를 추가하고 id가 2인 멤버를 찾아 이름을 "BC"로
                  변경한 뒤에 다시 조회해 이름이 "BC"로 변경됐는지 확인하는 형태입니다.
     */
    @Sql("/insert-members.sql")
    @Test
    void update() {
        // given
        Member member = memberRepository.findById(2L).get();

        // when
        member.changeName("BC");

        // then
        assertThat(memberRepository.findById(2L).get().getName()).isEqualTo("BC");
    }
    /*
        03 단계 - 그런데 해당 코드에는 아까 말한 @Transational 애너테이션이 없는데도 수정에 성공했음.
        이유는 @DataJpaTest 애너테이션 때문.
        @DataJpaTest - 테스트를 위한 설정을 제공하며, 자동으로 데이터베이스에 대한 트랜잭션 관리를 설정함.
        클래스에 있는 @DataJpaTest 애너테이션에 마우스 올리면 커서가 뜨는데 거기에 @Transactional이 있음을 확인 가능함.
        하지만 서비스 코드에서 업데이트 기능을 사용하려면 서비스 메서드에 반드시 @Transactional을 명시해야 하기 때문에
        앞으로는 @Transactional을 쓸 예정.

        5. 예제 코드 살펴 보기
            01 단계 Member.java 확인
     */
}