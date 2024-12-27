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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/new-article")
    // 1. id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑(id는 없을 수도 있음)
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {   // 2. id가 null이라면 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else {        // 3. id가 있으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
/*
    1. 쿼리 파라미터로 넘어온 id값은 newArticle() 메서드의 Long 타입 id 인자에 매핑합니다. 이 값은 없을 수도 있으므로
    2. id가 있으면 수정, 없으면 생성합니다.
        id가 없는 경우 기본 생성자를 이용해 빈 ArticleViewResponse 객체를 만들고, id가 있으면 기존 값을 가져오는 findById()
        메서드를 호출합니다.

    수정 / 생성 뷰 만들기

        01 단계 - 이제 컨트롤러 메서드에서 반환하는 newArticle.html을 구현하겠습니다.
            resources/templates 디렉토리에 newArticle.html 파일을 생성합니다.
 */
