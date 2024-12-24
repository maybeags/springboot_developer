package me.ahngeunsu.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.dto.ArticleListViewResponse;
import me.ahngeunsu.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);   // 1. 블로그 글 리스트 저장

        return "articleList";       // 2. articlesList.html라는 뷰 조회
    }
    /*
        1. addAttribute() 메서드를 사용해 모델에 값을 저장했습니다. 여기서는 "articles" 키에 블로그 글들을, 즉, 글 리스트를 저장
        2. 반환값인 "articlesList"는 resources/templates/articleList.html을 찾도록 뷰의 이름을 적은 겁니다.

        이 이후

        HTML 뷰 만들고 테스트 하기 -> 현재 여기까지 작업함 🎈
     */
}

