package me.ahngeunsu.springbootdeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.dto.AddUserRequest;
import me.ahngeunsu.springbootdeveloper.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);  // 회원 가입 메서드 호출
        return "redirect:/login";   // 회원 가입 완료된 이후에 로그인 페이지로 이동
    }
    /*
        회원 가입 처리가 된 다음에 로그인 페이지로 이동하기 위해 redirect: 접두사를 붙였습니다.
        이렇게 하면 회원 가입 처리가 끝나면 강제로 /login URL에 해당하는 화면으로 이동합니다.

        5. 회원 가입, 로그인 뷰 작성하기

            회원 가입과 로그인 코드를 모두 작성했으니 사용자가 회원 가입, 로그인 경로에 접근하면 회원 가입, 로그인 화면으로
            연결해주는 컨트롤러를 생성하고 사용자가 실제로 볼 수 있는 화면을 작성합니다.

            뷰 컨트롤러 구현하기
                01 단계 - 로그인, 회원 가입 경로로 접근하면 뷰 파일을 연결하는 컨트롤러를 생성합니다.
                    controller 디렉토리에 UserViewController.java 파일을 만들어 코드 작성
     */

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
    /*
        /logout GET 요청을 하면 로그아웃을 담당하는 핸들러인 SecurityContextHandler의 logout() 메서드를 호출해서
        로그아웃합니다.

        로그아웃 뷰 추가하기
            01 단계 - 로그아웃을 위한 뷰를 작성할겁니다.
                templates의 articleLst.html 파일을 열어준 뒤 [로그아웃] 버튼을 추가해주세요.
     */
}
