package me.ahngeunsu.springbootdeveloper.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.config.jwt.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 요청 헤더의 Authorization 키의 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        // 가져온 값에서 접두사 제거
        String token = getAccessToken(authorizationHeader); // getAccessToken method 생성해야합니다.
        // 가져온 토큰이 유효한지 확인하고, 유효한 때는 인증 정보 정보 설정
        if (tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
    /*
        요청 헤더에서 키가 'Authorization'인 필드의 값을 가져온 다음 토큰의 접두사 Bearer를 제외한 값을 얻습니다. 만약 값이 null이거나
            Bearer로 시작되지 않으면 null을 반환합니다. 이어서 가져온 토큰이 유효한지 확인하고, 유효하다면 인증 정보를 관리하는
            시큐리티 컨텍스트에 인증 정보를 설정합니다. 위에서 작성한 코드가 실행되며 인증 정보가 설정된 이후에 컨텍스트 홀더에
            getAuthentication() 메서드를 사용해 인증 정보를 가져오면 유저 객체가 반환됩니다. 유저 객체에는 유저이름(username)과
            권한 목록(authorities)과 같은 인증 정보가 포함됩니다.

            3. 토큰 API 구현하기
                이제 리프레시 토큰을 전달받아 검증하고, 유효한 리프레시 토큰이라면 새로운 액세스 토큰을 생성하는 토큰 API를 구현합니다.
                토큰 서비스, 컨트롤러를 차례로 구현 예정

                토큰 서비스 추가하기
                    리프레시 톸느을 전달받아 토큰 제공자를 사용해 새로운 액세스 토큰을 만드는 토큰 서비스 클래스를 생성 예정

                    01 단꼐 - UserService.java 파일로 이동해 전달받은유저 ID로 유저를 검색해서 전달하는 findById() 메서드를 추가로
                        구혆해봅니다.

                        UserService.java로 이동합니다.
     */
}
