package me.ahngeunsu.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.Article;
import me.ahngeunsu.springbootdeveloper.dto.ArticleListViewResponse;
import me.ahngeunsu.springbootdeveloper.dto.ArticleViewResponse;
import me.ahngeunsu.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

        HTML 뷰 만들고 테스트 하기 -> 현재 여기까지 작업함 🎈\
            01 단계 - resources/templates 디렉토리에 articleList.html을 만들고, 모델에 전달한 블로그 글 리스트
                개수만큼 반복해 글 정보를 보여주도록 코드를 작성할 예정
     */

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
    /*
        HTML 뷰 만들기
            01 단계 - resources/templates 디렉토리에 article.html을 만들어 화면을 작성합니다.
     */
}

