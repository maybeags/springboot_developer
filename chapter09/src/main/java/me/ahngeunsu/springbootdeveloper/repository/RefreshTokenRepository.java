package me.ahngeunsu.springbootdeveloper.repository;

import me.ahngeunsu.springbootdeveloper.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    /*
        토큰 필터 구현하기
            필터는 실제로 각종 요청을 처리하기 위한 로직으로 전달되기 전후에 URL 패턴에 맞는 모든 요청을 처리하는 기능 제공.
            요청이 오면 헤더값을 비교해서 토큰이 있는지 확인하고 유효 토큰이라면 시큐리티 콘텍스트 홀더(Security Context ?holder)에
            인증 정보를 저장

            시큐리티 컨텍스트(Security Context) : 인증 객체가 저장되는 보관소. 인증 정보가 필요할 때마다 여기서 인증 객체를 꺼내 사용 가능.
                이 클래스는 스레드마다 공간을 할당하는, 스레드 로컬(thread loca)에 저장되므로 코드 아무 곳에서나 참조할 수 있고, 다른 스레드와
                공유하지 않으므로 독립적으로 사용 가능,

            시큐리티 컨ㄴ텍스트 객첼ㄹ 저장하는 객체가 시큐리티 컨텍스트 홀더에 해다함.

            01 단계 - config 디렉토리에 TokenAuthenticationFilter.java 파일 생성.
                이 필터는 엑세스 토큰이 담긴 Authorization 헤더 값을 가져 온 뒤 액세스 토큰이 유효하다면 인증 정보를 설정


     */

}
