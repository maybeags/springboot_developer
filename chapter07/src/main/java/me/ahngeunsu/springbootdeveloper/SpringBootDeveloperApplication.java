package me.ahngeunsu.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing  // created_at, updated_at 자동 업데이트 -> 이후 컨트롤러 메서드 작성하기 -> 01 단계 - 뷰에서 사용할 DTO를 만듭니다. ArticleViewResponse를 생성한 뒤 클래스를 구현합니다.
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
/*
    1. 사전 지식 : 타임리프
        타임리프는 템플릿 엔진입니다. 그러면 템플릿 엔진이란? 스프링 서버에서 데이터를 받아 우리가 보는 웹 페이지, 즉 HTML 상에
        그 데이터를 넣어 보여주는 도구입니다. 다만 템플릿 엔진은 HTML과 함께 템플릿 엔진을 위한 문법을 살짝 섞어 사용해야 합니다.
            즉, HTML 지식이 약간 필요합니다.

        템플릿 엔진 개념 잡기
            ↓ 간단한 템플릿 문법을 위한 예
            <h1 text=${이름}>
            <p text=${나이}>

            h1 태그에는 ${이름}이 텍스트 어트리뷰트(속성)로 할당돼있습니다. p 태그도 비슷한 상황입니다. 이것이 템플릿 문법의 예시입니다.
            이렇게 해두면 서버에서 이름, 나이라는 키로 데이터를 템플릿 엔진에 넘겨주고 템플릿 엔진은 이를 받아 HTML에 값을 적용합니다.

            ↓ 서버에서 보내준 데이터 예
            {
                이름: "홍길동",
                나이: 11
            }
            값이 달라지면 그때그때 화면에 반영하니 동적인 웹 페이지를 만들 수 있게 되는 것입니다.

            템플릿 엔진은 각각 문법이 미묘하게 달라서 템플릿 엔지마다 문법을 새로 배워야 하는 단점이 있지만, 대부분의 구조는 비슷해서 한 번
            배워두면 다른 템플릿 엔진은 금방 익힐 수 있습니다.
                대표적인 템플릿 엔진 예시 : JSP, 타임리프, 프리마커 등

                하지만 스프링은 타임리프를 권장하고 있으므로 우리는 타임리프를 사용할 예정입니다.

                타임리프 표현식과 문법

                    타임리프의 문법은 직관적인 편인데, 자주 사용하는 표현식과 문법은 예시로 보여드리지만 구체적인 사용 방법은 실습을 통해
                    알아보겠습니다. 여기에서 소개하는 표현식들은 전달받은 데이터를 사용자들이 볼 수 있는 뷰로 만들기 위해 사용되는 표현식입니다.


                    🎈 타임리프 표현식
                    ${...} - 변수의 값 표현식
                    #{...} - 속성 파일 값 표현식
                    @{...} - URL 표현식
                    *{...} - 선택한 변수의 표현식. th:object에서 선택한 객체에 접근

                    🎈 타임리프 문법
                    th:text - 텍스트를 표현할 때 사용 - th:text=${person.name}
                    th:each - 컬렉션을 반복할 때 사용 - th:each="person:${persons}"
                    th:if - 조건이 true인 때만 표시 - th:if"${person.age}>=20"
                    th:unless - 조건이 false인 때만 표시 - th:unless"${person.age}>=20"
                    th:href - 이동 경로 - th:href="@{/persons(id=${person.id})}
                    th:with - 변수값으로 지정 - th:with="name=${person.name}"
                    th:object - 선택한 객체로 지정 - th:object="${person}"

        타임리프 사용을 위한 의존성 추가하기
            01 단계 - build.gradle 파일에 의존성을 추가하고 새로고침을 눌러 그레이들 변경 사항을 적용합니다.
            bundle.gradle로 이동했다가 다시 오세요.

        타임리프 문법 익히기용 컨트롤러 작성하기
            문법을 공부하기 위해 문법 익히기용 컨트롤러를 하나 임시로 만들 예정입니다.
            여기서는 /thymeleaf/example GET 요청이 오면 특정 데이터를 뷰, 즉 HTML로 넘겨주는 모델 객체에 추가하는 컨트롤러 메서드를
            작성할 예정입니다. 이 과정을 토앻 타임리프 문법에 익숙해지기 바랍니다.

            01 단계 - controller 패키지에 ExampleController.java 파일을 만들고 코드를 작성합니다.

            ExampleController.java 파일로 이동하세요.
 */
