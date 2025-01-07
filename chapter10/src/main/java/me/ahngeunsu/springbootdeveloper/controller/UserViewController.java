package me.ahngeunsu.springbootdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

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
