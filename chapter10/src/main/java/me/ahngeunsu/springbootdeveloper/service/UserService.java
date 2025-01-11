package me.ahngeunsu.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.User;
import me.ahngeunsu.springbootdeveloper.dto.AddUserRequest;
import me.ahngeunsu.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 1. 패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
    /*
        1. 패스워드를 저장할 때 시큐리티를 설정하며 패스워드 인코딩용으로 등록한 빈을 사용해서 암호화한 후에 저장

        컨트롤러 작성하기
            이제 회원 강비 폼에서 회원 가입 요청을 받으면 서비스 메서드를 사용해 사용자를 저장한 뒤, 로그인 페이지로
                이동하는 signup()메서드를 작성할 예정

                01 단계 - controller 디렉토리에 UserApiController.java 파일을 만들어 다음 코드를 입력
     */

    // 토큰 API 구현하기 - 토큰 서비스 추가하기에 해당
    // 메서드 추가
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
    /*
        02 단계 - service 디렉토리에 RefreshTokenService.java 파일을 생성합니다.
            전달 받은 리프레시 토큰으로 리프레시 토큰 객체를 검색해서 전달하는 findByRefreshToken() 메서드를 구현

            RefreshTokenService.java 파일을 생성하세요.
     */
}
