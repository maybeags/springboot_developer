package me.ahngeunsu.springbootdeveloper.repository;

import me.ahngeunsu.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);   // import java.util.Optional; 이거 import
}
/*
    이메일로 사용자를 식별 가능. 즉 email을 username으로 봐도 됩니다. 따라서 사용자 정보를 가져오기 위해서는
    스프링 시큐리티가 이메일을 전달받아야 합니다. 스프링 데이터 JPA는 메서드 규칙에 맞춰 메서드를 선언하면 이름을 분석해
    자동으로 쿼리를 생성해줍니다.

    findByEmail() 메서드는 실제 데이터베이스에 회원 정보를 요청할 때 다음 쿼리를 실행합니다.

    🍎findByEmail() 메서드가 요청하는 쿼리 예
        FROM users
        WHERE email = #{email}

    자주 사용하는 쿼리 메서드의 명명 규칙

    findByNAme() - "name" 컬럼의 값 중 파라미터로 들어오는 값과 같은 데이터 반환
        - WHERE name =?1
    findByNameAndAge() - 파라미터로 들어오는 값 중 첫 번째 값은 "name" 컬럼에서 조회하고, 두 번째 값은 "age" 컬럼에서 조회한 데이터에서 반환
        - WHERE name=?1 AND age =?2
    findByNameOrAge() - 파라미터로 들어오는 값 중 첫 번째 값이 "name" 컬럼에서 조회되거나 두 번째 값이 "age"에서 조회되는 데이터 반환
        - WHERE name=?1 OR age =?2
    findByAgeLessThan() - "age" 컬럼의 값 중 파라미터로 들어온 값보다 작은 데이터 반환
        - WHERE age <?1
    findByAgeGreaterThan() - "age" 컬럼의 값 중 파라미터로 들어온 값보다 큰 데이터 반환
        - WHERE age > ?1
    findByName(Is)Null() - "name" 컬럼의 값 중 null인 데이터 반환
        - WHERE name IS NULL

    서비스 메서드 코드 작성하기
        01 단계 - 엔티티와 리포지토리가 완성되었으니 스프링 시큐리티에서 로그인을 진행할 때 사용자 정보를 가져오는 코드를
            작성 예정. service 디렉토리에 UserDetailsService.java 파일을 생성하고 다음 코드를 작성하세요.
 */