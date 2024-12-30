package me.ahngeunsu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.User;
import me.ahngeunsu.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 사용자 이름(email)으로 사용자 정보를 가져오는 메서드
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
/*
    스프링 시큐리티에서 사용자 정보를 가져오는 UserDeailsService 인터페이스를 구현합니다(이름은 UserDetailService.java)
    필수로 구현해야 하는 loadUserByUsername() 메서드를 오버라이딩해서 사용자 정보를 가져오는 로직을 작성합님다.

    3. 시큐리티 설정하기
        01 단계 - 인증을 위한 도메인과 리포지토리, 서비스가 완성되었으니 실제 인증 처리를 하는 시큐리티 설정 파일
            WebSecurityConfig.java 파일을 작성.
            같은 패키지 내에 config 패키지를 새로 만들어 설정하면 됩니다.

            springbootdeveloper에 우클릭 -> config 패키지 생성 -> WebSecurityConfig.java 생성
 */
