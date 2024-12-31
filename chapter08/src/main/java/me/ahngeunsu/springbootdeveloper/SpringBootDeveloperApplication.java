package me.ahngeunsu.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
/*
    학습 목표 : 로그인, 회원 가입 기능을 구현합니다.
        회원 가입의 구조는 로그인 구조와 비슷합니다.

        /login 요청이 들어올 때
        -> UserViewController가 해당 요청에 대한 분기 처리를 하고 WebSecurityConfig에 설정한 보안 관련 내용들을 실행합
        -> UserDetailsService를 실행하면 요청을 성공했을 때
        -> defaultSuccessUrl로 설정한 /articles로 리다이렉트하거나 csrf를 disable하거나 하는 등의 작업

        UserDetailsService에서는 loadUserByUsername() 메서드를 실행하여 이메일로 유저를 찾고 반환.

        여기서 유저는 User 클래스의 객체이고 UserRepository에서 실제 데이터를 가져옵니다.

        회원 가입도 유사한 방식으로 구성할 예정입니다.

        로그아웃의 구성은 단순합니다.

        /logout 요청이 오면
        -> UserApiController 클래스에서 로그아웃 로직을 실행.

        로그아웃 로직은 SecurityContextLogoutHander에서 제공하는 logout() 메서드를 실행

        1. 사전 지식 : 스프링 시큐리티 🍎중요

            스프링 시큐리티(Spring Security) :
                1) 인증 vs 인가
                    (1) 인증(Authentication) : 사용자의 신원을 입증하는 과정
                        예를 들어 사용자가 사이트에 로그인할 때 누구인지 확인하는 과정을 인증

                    (2) 인가(Authorization) : 사이트의 특정 부분에 접근할 수 있는지 권한을 확인하는 작업
                        예를 들어 관리자는 관리자 페이지에 들어갈 수 있지만 일반 사용자는 관리자 페이지에 들어갈 수 없음.

                    인증과 인가 관련 코드를 아무런 도구 없이 작성하려면 복잡하기 때문에 스프링 시큐리티를 사용함.

                2) 스프링 시큐리티
                    스프링 시큐리티는 스프링 기반 애플리케이션의 보안을 담당하는 스프링 하위 프레임워크 보안 관련 옵션 다수 제공.
                        그리고 애너테이션으로 설정도 쉬운 편.
                        CSRF, 세선 공격을 방어해주고(-> 정처기 등에 자주 나옴), 요청 헤더도 보안 처리를 해주므로
                        보안 관련 개발을 하는데 부담을 줄여주는 편

                3) 필터 기반으로 동작하는 스프링 시큐리티
                    Spring Security Filter Chain 검색해서 이미지 일단 한 번 볼게요.

                    스프링 시큐리티의 풀 로그인을 설정하는 것은 간단할텐데 내부적으로는 복잡한 과정을 거칩니다.
                    모두 외워야 하는건 아니고, 로그인이 어떤 흐름으로 동작하는지 이해하면 더 잘 활용할 수 있을겁니다.
                    일단은 회원 가입과 로그인 관련 부분을 배운다고 생각하시면 됩니다.

    2. 회원 도메인 만들기
        스프링 시큐리티를 활용한 인증, 인가 기능을 구현 예정. 먼저 회원 정보를 저장할 테이블을 만들고,
        테이블과 연결할 도메인을 만든 다음, 이 테이블과 연결할 회원 엔티티를 만듭니다. 그리고 나서 회원
        엔티티와 연결되어 데이터를 조회하게해줄 리포지토리를 만든 후, 마지막으로 스프링 시큐리티에서 사용자 정보를
        가져오는 서비스를 만들 예정

        의존성 추가하기
            01 단계 - build.gradle 파일에 의존성 추가하고 돌아오세요.

        엔티티 만들기
            01 단계 - domain 패키지에 User.java 파일을 생성하고 UserDetails 클래스를 상속하는
                User 클래스를 생성

                해보세요/

 */