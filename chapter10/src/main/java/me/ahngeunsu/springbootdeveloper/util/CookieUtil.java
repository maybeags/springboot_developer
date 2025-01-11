package me.ahngeunsu.springbootdeveloper.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

public class CookieUtil {
    // 요청값(이름, 값, 만료 기간)을 바탕으로 쿠키 추가
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value); //import jakarta.servlet.http.Cookie;
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    //쿠키의 이름을 입력 받아 쿠키 삭제
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    // 객체를 직렬화해 쿠키의 값으로 변환
    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    // 쿠키를 역직렬화해 객체로 변환
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())
                )
        );
    }

    /*
        addCookie : 요청값(이름, 값, 만료 기간)을 바탕으로 HTTP 응답에 쿠키를 추가

        deleteCookie : 쿠키 이름을 입력 받아 쿠키를 삭제합니다. 실제로 삭제하는 방법은 없으므로 파라미터로 넘어온 키의 쿠키를 빈 값으로 바꾸고 만료 시간을
            0으로 설정해 쿠키가 재생성되자마자 만료 처리합니다.

        serialize : 객체를 직렬화해 쿠키의 값에 들어갈 값으로 변환

        deserialize : 쿠키를 역직렬화해 객체로 변환

        OAuth2 서비스 구현하기
            사용자 정보를 조회해 users 테이블에 사용자 정보가 있다면 리소스 서버에서 제공해주는 이름을
                업데이트. 사용자 정보가 없다면 users 테이블에 새 사용자를 생성해 데이터베이스에 저장
                하는 서비스를 구현합니다.

                    01 단계 - domain 패키지의 User.java 파일에 사용자 이름을 추가합니다.
     */
}
