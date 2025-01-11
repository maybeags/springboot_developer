package me.ahngeunsu.springbootdeveloper.config.oauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.config.jwt.TokenProvider;
import me.ahngeunsu.springbootdeveloper.domain.RefreshToken;
import me.ahngeunsu.springbootdeveloper.domain.User;
import me.ahngeunsu.springbootdeveloper.repository.RefreshTokenRepository;
import me.ahngeunsu.springbootdeveloper.service.UserService;
import me.ahngeunsu.springbootdeveloper.util.CookieUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);
    public static final String REDIRECT_PATH = "/articles";

    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        User user = userService.findByEmail((String) oAuth2User.getAuthorities().get("email"));

        // 1. 리프레시 토큰 생성 -> 저장 -> 쿠키에 저장
        String refreshToken = tokenProvider.generateToken(user, REFRESH_TOKEN_DURATION);
        // 이것도 나중에 정의할 예정입니다.
        saveRefreshToken(user.getId(), refreshToken);
        addRefreshTokenToCookie(request, response, refreshToken);
        // 2. 액세스 토큰 설정 -> 패스에 액세스 토큰 추가
        String accessToken = tokenProvider.generateToken(user, ACCESS_TOKEN_DURATION);
        // 이것도 나중에 정의할 예정
        String targetUrl = getTargetUrl(accessToken);
        // 3. 인증 관련 설정값, 쿠키 제거
        // 이것도 나중에 정의할 예정
        clearAuthenticationAttributes(request, response);
        // 4. 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    // 생성된 리프레시 토큰을 전달받아 데이터베이스에 저장
    private void saveRefreshToken(Long userId, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .map(entity -> entity.update(newRefreshToken))
                .orElse(new RefreshToken(userId, newRefreshToken));

        refreshTokenRepository.save(refreshToken);
    }

    // 생성된 리프레시 토큰을 쿠키에 저장
    private void addRefreshTokenToCookie(HttpServletRequest request,
                                         HttpServletResponse response,
                                         String refreshToken) {
        int cookieMaxAge = (int) REFRESH_TOKEN_DURATION.toSeconds();
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    // 인증 관련 설정값, 쿠키 제거
    private void clearAuthenticationAttributes(HttpServletRequest request,
                                               HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);

        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    // 액세스 토큰을 패스에 추가
    private String getTargetUrl(String token) {
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)
                .queryParam("token", token)
                .build()
                .toUriString();
    }
    /*
        스프링 시큐리티의 기본 로직에서는 별도의 authenticationSuccessHandler를 지정하지 않으면
        로그인 성공 이후 SimpleUrlAuthenticationSuccessHandler를 사용합니다. 일반적인 로직은 동일하게 사용하고,
        토큰과 관련된 작업만 추가로 처리하기 위해 SimpleUrlAuthenticationSuccessHandler을 상속 받은 뒤에
        onAuthenticationSuccess() 메서드를 오버라이드하겠습니다.

        1. 리프레시 토큰 생성, 저장, 쿠키에 저장
            토큰 제공자를 사용해 리프렛 토큰을 만든 뒤에 saveRefreshToken() 메서드를 호출해 해당
            리프레시 토큰을 데이터베이스에 유저 아이디와 함께 저장
            이후에 클라이언트에서 액세스 토큰이 만료되면 재발급 요청하도록
            addRefreshTokenToCookie() 메서드를 호출해 쿠키에 리프레시 토큰을 저장

        2. 액세스 토큰 생성, 패스에 액세스 토큰 추가
            토큰 제공자를 사용해 액세스 토큰을 만든 뒤에 쿠키에서 리다이렉트 경로가 담긴 값을 가져와 쿼리
            파라미터에 액세스 토큰을 추가합니다.

        3. 인증 관련 설정값, 쿠키 제거
            인증 프로세스를 진행하면서 세션과 쿠키에 임시로 저장해둔 인증 관련 데이터를 제거합니다.
            기본적으로 제공하는 메서드인 clearAuthenticationAttributes()는 그대로 호출하고
            removeAuthorizationRequestCookies()를 추가로 호출해 OAuth 인증을 위해 저장된 정보도
            삭제합니다.

        4. 리다이렉트
            2.에서 만든 URL로 리다이렉트합니다.

        글에 글쓴이 추가하기
        OAuth를 위한 로직이 모두 완성되었으므로 글에 글쓴이를 추가하는 작업을 진행하겠습니다.

            01 단계 - domain 패키지의 Article.java 파일을 연 다음 author 변수를 추가합니다.
            이후에 빌더 패턴에서도 author를 추가해 객체를 생성할 때 author를 입력 받을 수 있게 변경합니다.
     */
}
