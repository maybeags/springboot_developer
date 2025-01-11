package me.ahngeunsu.springbootdeveloper.config.oauth;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.User;
import me.ahngeunsu.springbootdeveloper.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws
            OAuth2AuthenticationException {
        // 요청을 바탕으로 유저 정보를 담은 객체 반환
        OAuth2User user = super.loadUser(userRequest);
        // 이거 아래에서 정의할겁니다. 오류 나는게 당연
        saveOrUpdate(user);
        return user;
    }

    // 유저가 있으면 업데이트, 없으면 유저 생성
    private User saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        User user = userRepository.findByEmail(email)
                .map(entity -> entity.update(name))
                .orElse(User.builder()
                        .email(email)
                        .nickname(name)
                        .build());
        return userRepository.save(user);
    }
    /*
        부모 클래스인 DefaultOAuth2UserService에서 제공하는 OAuth 서비스에서 제공하는 정보를
        기반으로 유저 객체를 만들어주는 loadUser() 메서드를 사용해 사용자 객체를 불러옵니다.
        사용자 객체는 식별자, 이름, 이메일, 프로필 사진 링크 등의 정보를 담고 있습니다.
        그리고 saveOrUpdate() 메서드는 사용자가 user 테이블에 있으면 업데이트하고 없으면 사용자를 새로
        생성해서 데이터베이스에 저장합니다.

        OAuth2 설정 파일 작성하기
            OAuth2와 JWT를 함께 사용하려면 기존 스프링 시큐리티를 구현하며 작성한 설정이 아니라
            다른 설정을 사용해야 합니다. OAuth2, JWT에 알맞게 설정 파일을 수정하겠습니다.

                01 단계 - 기존의 폼 로그인 방식을 사용하기 위해 구성했던 설정 파일인 WebSecurityConfig.java
                내용을 모두 주석처리합니다. -> 주석 처리하고 나서 02단계

                02 단계 - config 패키지에 WebOAuthSecurityConfig.java 파일을 생성하고
                    코드 작성합니다.
     */
}
