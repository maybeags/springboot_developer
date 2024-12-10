package me.ahngeunsu.springbootdeveloper;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }
    /*
        ObjectMapper - Jackson 라이브러리에서 제공하는 클래스로 객체와 JSON간의 변환을 처리해줌.

        예를 들어,

        Code code  new Code(13)
        objectMapper.writeValueAsString(code)

        라는 코드가 있다고 가정했을 때,
        writeValueAsString(code)와 같이 메서드를 호출하면

        {'value': 13}과 같은 JSON 형태의 문자열로 객체가 변환됨. 이를 객체 직렬화라고 말하기도 함.

        테스트 코드 연습 문제 풀어보기 -> 퀴즈마다 다루지 않은 메서드를 추가로 사용할 때는 힌트에 표시해둠.
     */

    // hint : 쿼리 파라미터를 추가하는 방법은 perform() 메서드에 param() 메서드를 체이닝하여 사용하면 됩니다.

    @DisplayName("quiz(): GET /quiz?code=1 이면 응답 코드는 201, 응답 본문은 Create!를 리턴한다.")
    @Test
    public void getQuiz1() throws Exception {
        // 여기에 코드를 작성해주세요.
        // given
        final String url = "/quiz";

        // when
        final ResultActions result = mockMvc.perform(get(url)
                .param("code", "1")
        );

        // then
        result.andExpect(status().isCreated())
                .andExpect(content().string("Create!"));

    }

    @DisplayName("quiz(): GET /quiz?code=2 이면 응답 코드는 400, 응답 본문은 Bad Request!를 리턴한다.")
    @Test
    public void getQuiz2() throws Exception {
        //여기에 코드를 작성해주세요.
        // given
        final String url = "/quiz";

        // when
        final ResultActions result = mockMvc.perform(get(url).param("code", "2")
        );

        // then
        result.andExpect(status().isBadRequest()).andExpect(content().string("Bad Request!"));
    }
    // 이거 다음은 postquiz

    /*
        문제 02 POST 요청을 보내 응답 코드마다 예상하는 응답을 반환하는지 검증하는 테스트를 작성하세요.
     */
    @DisplayName("quiz(): POST /quiz?code=1 이면 응답 코드는 403, 응답 본문은 Forbidden!를 리턴한다.")
    @Test
    public void postQuiz1() throws Exception {
        // 여기에 코드를 작성해주세요.

        // 정답
        // given
        final String url = "/quiz";

        // when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(1)))
        );

        // then
        result.andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden!"));
    }

    @DisplayName("quiz(): POST /quiz?code=13 이면 응답 코드는 200, 응답 본문은 OK!를 리턴한다.")
    @Test
    public void postQuiz13() throws Exception {
        // 여기에 코드를 작성해주세요.

        // 정답
        // given
        final String url = "/quiz";

        // when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(13)))
        );

        // then
        result.andExpect(status().isOk())
                .andExpect(content().string("OK!"));
    }
}