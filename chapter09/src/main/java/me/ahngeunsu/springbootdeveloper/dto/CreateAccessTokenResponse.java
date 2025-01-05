package me.ahngeunsu.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAccessTokenResponse {
    private String accessToken;

    /*
        02 단계 - 실제로 요청을 받고 처리할 컨트롤러를 생성합니다. /api/token POST 요청이 오면 토큰 서비스에서 리프레시 토큰을 기반으로
            새로운 액세스 토큰을 만들어주면 됩니다. controller 패키지에 TokenApiController.java 파일을 만들고 코드 입력하세요.
     */
}
