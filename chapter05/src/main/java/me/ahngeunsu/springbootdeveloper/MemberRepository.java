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
}
