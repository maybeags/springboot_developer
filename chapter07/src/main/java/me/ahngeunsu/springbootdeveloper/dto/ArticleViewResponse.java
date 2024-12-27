package me.ahngeunsu.springbootdeveloper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.Article;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
    /*
        02 단계 - 이제 블로그 글을 반환할 컨트롤러의 메서드를 작성하겠습니다.
        BlogViewController.java 파일을 열어 getArticle() 메서드를 추가하겠습니다.
     */
}
