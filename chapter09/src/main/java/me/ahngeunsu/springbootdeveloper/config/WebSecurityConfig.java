package me.ahngeunsu.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailService userService;

    // 1. 스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    // 2. 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeRequests(auth -> auth         // 3. 인증, 인가 설정
                        .requestMatchers(
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/signup"),
                                new AntPathRequestMatcher("/user")
                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin       // 4. 폼 기반 로그인 설정
                        .loginPage("/login")
                        .defaultSuccessUrl("/articles")
                )
                .logout(logout -> logout                // 5. 로그아웃 설정
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    // 7. 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);    // 8. 사용자 정보 서비스 설정
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return new ProviderManager(authProvider);
    }

    // 9. 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

/*
    1. 스프링 시큐리티의 모든 기능을 사용하지 않게 설정하는 코드
        즉 인증, 인가 서비스를 모든 곳에 적용하지는 않음.
        ㅇㄹ반적으로 정적 리소스(이미지, HTML 파일)에 설정.
        정적 리소스만 스프링 시큐리티 사용을 비활성화 하는 데, static 하위 경로에 있는 리소스와
        h2 데이터를 확인하는 데 사용하는 h2-console 하위 url을 대상으로 ignoring() 메서드를 사용

    2. 특정 HTTP 요청에 대해 웹 기반 보안을 구성. 이 메서드에서 인증/인가 및 로그인, 로그아웃 관련 설정 가능

    3. 특정 경로에 대한 액세스 설정.
        requestMatchers() : 특정 요청과 일치하는 url에 대한 액세스를 설정
        permitAll() : 누구나 접근이 가능하게 설정. 즉, "/login", "/signup", "/user"로 요청이 오면,
            인증/인가 없이도 접근 가능
        anyRequest() : 위에서 설정한 url 이외의 요청에 대해서 설정
        authenticatedI() : 별도의 인가는 필요하지 않지만 인증이 성공된 상태여야 접근 가능

    4. 폼 기반 로그인을 설정.
        loginPage() : 로그인 페이지 경로를 설정
        defaultSuccessIrl() : 로그인이 완료 되었을 때 이동할 경로를 설정

    5. 로그아웃 설정.
        logoutSuccessUrl() : 로그아웃이 완료되었을 때 이동할 경로를 설정
        invalidHttpSession() : 로그아웃 이후에 세션을 전체 삭제할지 여부를 설정

    6. csrf 설정을 비활성화 - CSRF 공격을 방지하기 위해서는 활성화하는게 좋지만 실습을 편리하게 하기 위해 비활성화

    7. 인증 관리자 관련 설정. 사용자 정보를 가져올 서비스를 재정의하거나, 인증 방법, 예를 들어
        LADP, JDBD 기반 인증 등을 설정할 때 사용

    8. 사용자 서비스를 설정
        userDetailsService() : 사용자 정보를 가져올 서비스를 설정. 이 때 설정하는 서비스 클래스는 반드시
            USerDetailsService를 상속받은 클래스여야 함.
        passwordEncoder() : 비밀번호를 인코더를 설정

    9. 패스워드 인코더를 빈으로 등록

    4. 회원 가입 구현하기
        시큐리티 설정이 완료되었으니 회원 가입을 구현하겠습니다. 회원 정보를 추가하는 서비스 메서드를 작성한 뒤에
            회원 가입 컨트롤러를 구현함.

            서비스 메서드 코드 작성하기
                01 단계 - 사용자 정보를 담고 있는 객체를 작성
                    dto 디렉토리에 AddUserRequest.java 파일을 추가한 뒤 코드 작성
 */