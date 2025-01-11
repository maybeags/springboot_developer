package me.ahngeunsu.springbootdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/login")
    public String login() {
//        return "login"; -> author 추가 전
        return "oauthLogin";
    }
    /*
        OAuth뷰 구성하기 2 단계입니다 -> 한참 뒤에 하는 거임 조심
        02 단계 - 로그인 화면에서 사용할 이미지를 구글 로그인 브랜드 페이지에서 다운로드하겠습니다.
        https://developers.google.com/identity/branding-guidelines에 접속한 다음 [파일 다운로드] 버튼을 눌러 이미지
        파일을 다운로드하세요.
     */

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    /*
        /login 경로로 접근하면 login() 메서드가 login.html을, /signup 경로에 접근하면 signup() 메서드는
        signup.html을 반환할 예정

        뷰 작성하기
            01 단계 - 이제 뷰 작성할 예정. templates 디렉토리에 login.html을 생성
     */
}
