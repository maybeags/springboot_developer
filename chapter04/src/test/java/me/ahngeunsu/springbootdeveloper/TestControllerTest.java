package me.ahngeunsu.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
    02 단계

    생성된 파일을 다음과 같이 작성.
    테스트 코드를 작성하기 위해 또 새로운 애너테이션 사용 예정
    많은 애너테이션을 공부하는 것은 부담스럽게 느껴질 수도 있겠지만 실무에서 자주 사용하니까 알아두시기 바랍니다.
 */
@SpringBootTest         // 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc   // MockMvc 자동 생성 및 자동 구성
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

/*
        @SpringBootTest
            메인 애플리케이션 클래스에 추가하는 애너테이션인 @SpringBootApplication이 있는 클래스를 찾고,
            그 클래스에 포함돼 있는 빈을 찾은 다음 테스트용 애플리케이션 컨텍스트라는 것을 만듭니다.

        @AutoConfigureMockMvc
            MockMvc를 생성하고 자동으로 구성하는 애너테이션
            MockMvc는 애플리케이션을 서버에 배포하지 않고도 테스트용 MVC 환경을 만들어 요청 및 전송, 응답 기능을 제공하는
            유틸리티 클래스. 즉, 컨트롤러를 테스트할 때 사용되는 클래스

    03 단계 - 여기에 계속 작성하면 완료됨. TestController의 로직을 테스트하는 코드 작성 예정
 */
    @DisplayName("getAllMembers : 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        // given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when
        final ResultActions result = mockMvc.perform(get(url)   // 1.
                        .accept(MediaType.APPLICATION_JSON));   // 2.

        //then
        result
                .andExpect(status().isOk())                     // 3.
                // 4. 응답의 0번째 갑이 DB에 저장한 값과 같은지 확인
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));

        /*
            이까지 작성이 끝나면 Given-When-Tehn 패턴이 적용되어 있음을 확인 할 수 있습니다.

            해당 코드에서의 패턴을 살펴봅니다.
            Given : 멤버를 저장
            When : 멤버 리스트를 조회하는 API를 호출
            Then : 응답 코드가 OK이고, 반환받은 값 중에 0번째 요소의 id와 name이 저장된 값과 같은지 확인

            1. perform() 메서드는 요청을 전송하는 역할을 하는 메서드
                -> 그 결과로 ResultActions 객체를 받으며, ResultActions 객체는 반환값을 검증하고 확인하는
                andExpect() 메서드를 제공해줍니다.
            2. accept() 메서드는 요청을 보낼 때 무슨 타입으로 응답을 받을지 결정하는 메서드
                JSON, XML 등 다양한 타입이 있지만, 여기에서는 JSON을 받는다고 명시해두도록 합니다.
            3. andExpect() 메서드는 응답을 검증. TestController에서 만든 API는 응답으로 OK(200)을 반환하므로
                이에 해당하는 메서드인 isOk를 사용해 응답 코드가 OK(200)인지 확인합니다.

            4, jsonPath("$[0].${필드명}")은 JSON 응답값을 가져오는 역할을 하는 메서드입니다. 0번째 배열에 들어 있는
                객체의 id, name값을 가져오고 저장된 값과 같은지 확인합니다.

            HTTP 주요 응답 코드

            200 OK - isOk() - HTTP 응답 코드가 200 OK인지 검증
            201 create - isCreated() - HTTP 응답 코드가 201 Created인지 검증
            400 Bad Request - isBadRequest() - HTTP 응답 코드가 400 Bad Request인지 검증
            403 Forbidden - isForbidden() - 응답 코드가 403 Forbidden인지 검증
            404 Not Found - isNotFound() - 응답 코드가 404 Not Found인지 검증
            400번대 응답 코드 - is4xxClientError() - 응답 코드가 400번대 응답 코드인지 검증
            500 Internal server Error - isInternalServerError() - 500 Internal Server Error인지 검증
            500번대 응답 코드 - is5xxServerError - 응답 코드가 500번대 응답 코드인지 검증

            이까지 하고

            테스트 코드 패턴 연습하기 -> src-main-java-me.ahngeunsu.springbootdeveloper 폴더에 QuizController.java 파일 추가할 것
         */
    }
}