package me.ahngeunsu.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.config.jwt.TokenProvider;
import me.ahngeunsu.springbootdeveloper.config.oauth.OAuth2UserCustomService;
import me.ahngeunsu.springbootdeveloper.repository.RefreshTokenRepository;
import me.ahngeunsu.springbootdeveloper.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {

    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer configure() {  // 스프링 시큐리티 비활성화
            return (web) -> web.ignoring()
                    .requestMatchers(toH2Console())
                    .requestMatchers(
                            new AntPathRequestMatcher("/img/**"),
                            new AntPathRequestMatcher("/css/**"),
                            new AntPathRequestMatcher("/js/**")
                    );

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1. 토큰 방식으로 인증을 하기 때문에 기존에 사용하던 폼 로그인, 세션 비활성화
        return http
                .csrf(AbstractHttpConfigurer::disable)   //import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 2. 헤더를 확인할 커스텀 필터 추가
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 위에 tokenAuthenticationFilter()는 정의할 예정
                // 3. 토큰 재발급 URL은 인증 없이 접근 가능하도록 설정, 나머지 API URL은 인증 필요
                .authorizeRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/api/token")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/**")).authenticated()
                        .anyRequest().permitAll())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        // Authorization 요청과 관련된 상태 저장
                        .authorizationEndpoint(authorizationEndpoint ->
                                // oAuth2AuthorizationRequestBasedOnCookieRepository -> 나중에 정의
                                authorizationEndpoint.authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository()))
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(oAuth2UserCustomService))
                        // 인증 성공 시 실행할 핸들러 -> oAuth2SuccessHandler 나중에 정의
                        .successHandler(oAuth2SuccessHandler())
                        )
                    // 5. /api로 시작하는 url인 경우 401 상태 코드를 반환하도록 예외 처리
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .defaultAuthenticationEntryPointFor(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                                new AntPathRequestMatcher("/api/**")
                        ))
                .build();

    }

    // 다음에 작성할 파일이므로 빨간줄 생겨도 괜찮습니다.
    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler() {
        return new OAuth2SuccessHandler(tokenProvider,
                refreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository(),
                userService
        );
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    // 이것도 다음에 작성할 파일이므로 빨간줄 생겨도 괜찮습니다.
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
/*
    1. filterChain() 메서드
        토큰 인증 방식으로 인증하므로 기존 폼 로그인, 세션 기능을 비활성화합니다.

    2. addFilterBefore() 메서드
        헤더 값을 확인할 커스텀 필터를 추가합니다. 이 필터는 chapter09에서 구현한
        TokenAuthenticationFilter 클래스

    3. authorizeRequest() 메서드 URL 인증 설정
        토큰 재발급 URL은 인증 없이 접근하도록 설정하고 나머지 API들은 모두 인증을 해야 접근하도록 설정

    4-5. oauth2Login() 메서드 이후 체인 메서드 수정
        OAuth2에 필요한 정보를 세션이 아닌 쿠키에 저장해서 쓸 수 있도록 인증 요청과 관련된 상태를
        저장할 저장소를 설정합니다. 인증 성공 시 실행할 핸들러도 설정.
        해당 클래스는 아직 구현하지 않았으므로 에러가 발생합니다.
        다음에 코드 작성 예정

    6. exceptionHandling() 메서드 예외 처리 설정
        /api로 시작하는 url인 경우 인증 실패 시 401 상태 코드, 즉 Unauthorized를 반환

    03 단계 - OAuth2에 필요한 정보를 세션이 아닌 쿠키에 저장해서 쓸 수 있도록 인증 요청과 관련된 상태를
        저장할 저장소를 구현.
        config/oauth 패키지에 OAuth2AuthorizationRequestBasedOnCookie.java 파일을 생성
 */