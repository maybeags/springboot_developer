package me.ahngeunsu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.config.jwt.TokenProvider;
import me.ahngeunsu.springbootdeveloper.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
    /*
        컨트롤러 추가하기
            토큰을 생성하고, 유효성을 검증하는 로직을 모두 작성했으니 실제로 토큰을 발급받는 API를 생성

            01 단계 - dto 패키지에 토큰 생성 요청 및 응답을 담당할 DTO인 CreateAccessTokenRequest와 CreateAccessTokenResponse
                클래스를 생성합니다.

                만드세요.
     */
}
