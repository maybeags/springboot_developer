package me.ahngeunsu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.RefreshToken;
import me.ahngeunsu.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("unexpected token"));
    }
    /*
        03 단계 - 토큰 서비스 클래스를 생성합니다. servicee 디렉토리에 TokenService.java 파일을 생성해서 코드 입력할겁니다.
            createNewAccessToken() 메서드는 전달받은 리프레스 토큰으로 토큰 유효성 검사를 진행하고, 유효한 토큰일 때 리프레시 토큰으로
            사용자 ID를 찾습니다. 마지막으로는 사용자 ID로 사용자를 찾은 후에 토큰 제공자의 generateToken() 메서드를 호출해서 새로운
            액세스 토큰을 생성합니다.

            service 디렉토리 -> TokenService.java 생성합니다.
     */
}
