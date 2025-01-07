package me.ahngeunsu.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.dto.CreateAccessTokenRequest;
import me.ahngeunsu.springbootdeveloper.dto.CreateAccessTokenResponse;
import me.ahngeunsu.springbootdeveloper.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken
            (@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.
                getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }
    /*
        03 단계 - 테스트 코드 작성을 통해 실제로 어떻게 동작하는지 확인할겁니다.
            test/.../controller 패키지에 TokenApiControllerTest.java 파일을 만들고 코드를 작성할겁니다.
            여기서 토큰을 생성하는 메서드인 createNewAccessToken() 메서드에 대하 테스트 코드를 작성할겁니다.
     */
}
