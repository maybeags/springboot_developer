package me.ahngeunsu.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.Article;
import me.ahngeunsu.springbootdeveloper.dto.AddArticleRequest;
import me.ahngeunsu.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // HTTP Reponse Body에 객체 데이터를 JSOM 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST 일 때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // @RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }
    /*
        @RestController 애너테이션을 클래스에 붙이면 HTTP 응답으로 객체 데이터를 JSON 형식으로 반환합니다.
        @PostMapping() 애너테이션은 HTTP 메서드가 POST일 때 요청 받은 URL 과 동일한 메서드와 매핑합니다.
        지금의 경우 /api/articles는 addArticles() 메서드에 매핑합니다.
        @RequesteBody 애너테이션은 HTTP 요청을 할 때, 응답에 해당하는 값을 @RequestBody 애너테이션을이 붙은
        대상 객체인 AddArticleRequest에 매핑합니다.
        ResponseEntity.status().body()는 응답 코드로 201, 즉 Created를 응답하고 테이블에 저장된 객체를 반환합니다.

        꼭 알아두면 좋을 응답 코드

        200 OK : 요청이 성공적으로 수행되었음
        201 Created : 요청이 성공적으로 수행되었고, 새로운 리소스가 생성되었음
        400 Bad Request : 요청 값이 잘못되어 요청에 실패했음
        403 Forbidden : 권한이 없어 요청에 실패했음
        404 Not Found : 요청 값으로 찾은 리소스가 없어 요청에 실패했음
        500 Internal Server Error : 서버 상에 문제가 있어 요청에 실패했음

        이까지 하고 API가 완성됐으니 API가 잘 동작하는지 테스트 할겁니다.

        API 실행 테스트하기
            실제 데이터를 확인하기 위해 H2 콘솔을 활성화해야 합니다. 현재는 H2 콘솔이 비활성화 돼있으므로
            속성 파일을 수정해줘야 합니다.

            01 단계 - resource 폴더에 application.yml 파일이 있습니다. 파일을 열어 코드를 추가합니다.

            02 단계 - 스프링부트 서버를 실행하세요. 이후 포스트맨 실행합니다.
            HTTP 메서드는 POST로, URL에는 http://localhost:8080/api/articles, BODY는 raw->JSON으로 변경한 다음
            요청 창에
            {
                "title": "제목",
                "content": "내용"
            }
            으로 작성하고, Send 눌러 요청을 보내세요. Body에 pretty모드로 결과를 보여줄 겁니다.
            실제 값이 스프링 부트 서버에 저장된 것입니다. 해당 과정이 HTTP 메서드 POST로 서버에 요청해 값을 저장하는
            과정에 해당합니다.

            -> 일단 여기까지 성공

            응값 값을 보면 데이터가 잘 저장된 것 확인할 수 있음. 실제로도 그런지 확인해봐야하는데, 즉
            H2 데이터베이스에 잘 저장됐는지 확인해봅니다.

            03 단계 - 웹 브라우저에서 localhost:8080/h2-console에 접속해봅니다. 스프링 부트 서버는
                켠 상태를 유지해야 합니다.

                Driver Class : org.h2.Driver
                JDBC URL : jdbc:h2:mem:testdb
                User Name : sa 로 입력하고 Connect 눌러서 로그인합니다. 이렇게 하면 스프링 부트 서버 안에
                내장돼있는 H2 데이터베이스에 접속하고 데이터를 확인할 수 있게 됩니다.

            04 단계 - SQL statement:의 입력 창에 SELECT * FROM ARTICLE을 입력한 뒤 Run을 눌러 쿼리를 실행합니다.
            이렇게 하면 H2 데이터베이스에 저장된 데이터를 확인할 수 있습니다. 그리고 왼쪽을 보면 ARTICLE이라는 테이블도 보입니다.
            애플리케이션을 실행하면 자동으로 생성한 엔티티 내용을 바탕으로 테이블이 생성되고, 우리가 요청한 POST 요청에 의해
            실제로 데이터가 저장된 것입니다.

        반복 작업을 줄여 줄 테스트 코드 작성하기


     */
}
