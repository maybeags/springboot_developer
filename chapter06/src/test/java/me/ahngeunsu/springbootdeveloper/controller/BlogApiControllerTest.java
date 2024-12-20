package me.ahngeunsu.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ahngeunsu.springbootdeveloper.domain.Article;
import me.ahngeunsu.springbootdeveloper.dto.AddArticleRequest;
import me.ahngeunsu.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트
@AutoConfigureMockMvc   // MockMvc 생성 및 자동 구성
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;    // 직렬화, 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach // 테스트 실행 전 실행하는 메서드
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }

    /*
        테스트 관련 애너테이션은 이미 실습했고, 새로 보이는 것은 ObjectMapper 클래스입니다.
        이 클래스로 만든 객체는 자바 객체를 JSON 데이터로 변환하는 직렬화, 반대로 JSON 데이터를
        자바에서 사용하기 위해 자바 객체로 변환하는 역직렬화를 사용합니다.

        직렬화(Serialization) - 자바 객체를 JSON 데이터 형태로 변환
        역직렬화(Deserialization) - JSON 데이터를 자바 객체로 변환

        HTTP에서는 JSON을, 자바에서는 객체를 사용합니다. 하지만 서로 형식이 다르기 때문에 형식에 맞게
        변환하는 작업이 필요합니다. 해당 작업을 직렬화, 역직렬화라고 합니다.
            1) 직렬화란 자바 시스템 내부에서 사용되는 객체를 외부에서 사용하도록 데이터를 작업을 의미함.
            예를 들어 title은 "제목", content는 "내용"이라는 값이 들어있는 개체가 있다고 가정하겠습니다.
            즉, 자바상에서는

            @AllArgsConstructor
            public class Article {
                private title;
                private content;

                main메서드 {
                    Ariticle article1 = new Article("제목", "내용");
                }
            }
        형태로 작성하게 되는데, JSON 데이터 상으로는 포스트맨에서 봤던 것처럼,
        {
            "title": "제목",
            "content": "내용"
        }
        형태로 정리가 됩니다. 이렇게 바꿔주는 과정을 직렬화, 반대가 역직렬화에 해당합니다.

        02 단계 - 이제 블로그 글 생성 API를 테스트하는 코드를 작성합니다. given-when-then 패턴을 생성할 코드의 내용은 다음과
        같습니다. 여기에 바로 작성합니다.

            given - 블로그 글 추가에 필요한 요청 객체를 만듭니다.
            when - 블로그 글 추가 API에 요청을 보냅니다. 이때 요청 타입은 JSON이며, given 절에서 미리 만들어둔 객체를
                요청 본문으로 함께 보냅니다.
            then - 응답 코드가 201 created인지 확인합니다. Blog를 전체 조회해 크기가 1인지 확인하고, 실제로 저장된 데이터와
                요청 값을 비교합니다.
     */

    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        // 객체 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        // 설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);   // 크기가 1인지 검증
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);

    }
    /*
        writeValueAsString() 메서드를 통해 객체를 JSON으로 직렬화해줍니다. 그 이후에는 MockMvc를 사용해 HTTP 메서드,
        URL, 요청 본문, 요청 타입 등을 설정한 뒤 설정한 내용을 바탕으로 테스트 요청을 보냅니다. contentType() 메서드는 요청을 보낼 때
        JSON, XML 등 다양한 타입 중 하나를 골라 요청을 보냅니다. 여기에서는 JSON 타입의 요청을 보낸다고 명시했습니다.
        assertThat() 메서드로는 블로그 글의 개수가 1인지 확인합니다. 자주 사용하는 메서드를 정리하겠습니다.

        assertThat(articles.size()).isEqualTo(1);   - 블로그의 글 크기가 1이어야 합니다.
        assertThat(articles.size()).isGreaterThan(2);   - 블로그의 글 크기가 2보다 커야 합니다.
        assertThat(articles.size()).isLessThan(5);   - 블로그의 글 크기가 5보다 작아야 합니다.
        assertThat(articles.size()).isZero();   - 블로그의 글 크기가 0이어야 합니다.
        assertThat(article.title()).isEqualTo("제목");   - 블로그의 글의 title 값이 "제목"이어야 합니다.
        assertThat(article.title()).isNotEmpty();   - 블로그의 글의 title 값이 비어있지 않아야 합니다.
        assertThat(article.title()).contains("제");   - 블로그의 글의 title 값이 "제"를 포함해야 합니다.

        03 단계 - 테스트 코드를 실행해 코드가 잘 동작하는지 확인합니다.


     */
}