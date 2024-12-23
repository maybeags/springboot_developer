package me.ahngeunsu.springbootdeveloper.service;
/*
    서비스 메서드 코드 작성하기 02 단계 중입니다.
 */
import lombok.RequiredArgsConstructor;
import me.ahngeunsu.springbootdeveloper.domain.Article;
import me.ahngeunsu.springbootdeveloper.dto.AddArticleRequest;
import me.ahngeunsu.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service                    // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    /*
        @Service 애너테이션은 해당 클래스를 빈으로 서블릿 컨테이너에 등록해줍니다.
        save() 메서드는 JpaRepository에서 지원하는 저장 메서드 save()로,
        AddArticleRequest 클래스에 저장된 값들을 article 데이터베이스에 저장합니다.

        컨트롤러 메서드 코드 작성하기
            이제 URL에 매핑하기 위한 컨트롤러 메서드를 추가합니다. 컨트롤러 메서드 구현 학습한 적 있습니다.
            컨트롤러 메서드에는 URL 매핑 애너테이션 @GetMapping, @PostMapping, @PutMapping, @DeleteMapping 등을
            사용할 수 있습니다. 이름에서 볼 수 있듯이 각 메서드는 HTTP 메서드에 대응합니다.
            여기에서는 /api/articles에 POST 요청이 오면 @PostMApping을 이용해 요청을 매핑한 뒤,
            블로그 글을 생성하는 BlogService의 save() 메서드를 호출한 뒤, 생성된 블로그 글을 반환하는 작업을 할
            addArticle() 메서드를 작성할 예정입니다.

            01 단계 - springbootdelveloper 패키지에 controller 패키지를 생성한 뒤, BlogApiController.java 파일 생성합니다.

     */
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    /*
        JPA 지원 메서드인 findAll()을 호출해 article 테이블에 저장되어 있는 모든 데이터를 조회합니다. 이제 요청을 받아 서비스에 전달하는
        컨트롤러를 만들겠습니다.

        컨트롤러 메서드 코드 작성하기
        /api/articles GET 요청이 오면 글 목록을 조회할 findAllArticles() 메서드를 작성할 예정. 이 메서드는 전체 글 목록을 조회하고 응답하는 역할

            01 단계 - 응답을 위한 DTO를 먼저 작성함. dto 디렉토리에 ArticleResponse.java 파일을 생성.
     */

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
    /*
        여기서 구현한 findById() 메서드는 JPA에서 제공하는 findById() 메서드를 사용해 ID를 받아 엔티티를 조회하고, 없으면
        IllegalArguemntException 예외를 발생합니다.

        컨트롤러 메서드 코드 작성하기

            01 단계 -
     */

}
