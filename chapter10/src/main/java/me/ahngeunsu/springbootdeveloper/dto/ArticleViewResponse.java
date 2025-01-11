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
    private String author;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.author = article.getAuthor();
    }
    /*
        author 추가 후에
        06 단계 - 스프링 부트 애플리케이션이 실행될 때마다 데이터를 추가하기 위해 data.sql 파일에도 author 컬럼 추가합니다.

        추가 후에
        07 단계 - 이제 븅서 글쓴이의 정보를 알 수 있게 뷰를 수정해보겠습니다. article.html 파일을 연 다음
            글쓴이의 정보를 가져올 수 있게 코드를 수정합니다.
            resources/templates/article.html
     */
}
