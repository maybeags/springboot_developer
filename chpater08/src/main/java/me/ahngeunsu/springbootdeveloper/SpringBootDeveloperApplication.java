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
 */