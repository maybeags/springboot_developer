package me.ahngeunsu.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt") // 자바 클래스에 프로피트값을 가져와 사용하는 애너테이션
public class JwtProperties {
    private String issuer;
    private String secretKey;

//    이렇게하면 issuer 필드에는 application.yml에서 설정한 jwt.issuer 값이, secretKey에는
//    jwt.secret_key값이 매핑 됩니다.

    /*
        03 단계 - 계속해서 토큰을 생성하고 올바른 토큰인지 유효성 검사를 하고, 토큰에서 필요한 정보를
            가져오는 클래스를 작성.
             config/jwt 디렉토리에 TokenProvider.java파일 생성하세요.
     */
}
