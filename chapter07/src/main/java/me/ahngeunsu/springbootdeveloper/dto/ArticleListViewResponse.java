package me.ahngeunsu.springbootdeveloper.dto;

import lombok.Getter;
import me.ahngeunsu.springbootdeveloper.domain.Article;

@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
    /*
        02 단계 - controller 패키지에 BlogViewController.java 파일을 만들어 /articles GET 요청을 처리할 코드를 작성합니다.
            여기서는 블로그 글 전체 리스트를 담은 뷰를 반환합니다.

            controller 패키지 -> BlogViewController.java 파일을 생성하세요.
     */
}
