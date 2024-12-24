package me.ahngeunsu.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller // 컨트롤러임을 명시
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String tymeleafExample(Model model) {    // import org.springframework.ui.Model 하세요  // 뷰로 데이터를 넘겨주는 모델 객체
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson);    // Person 객체 저장
        model.addAttribute("today", LocalDate.now());    // Person 객체 저장

        return "example";   // example.html라는 뷰 조회
    }

    @Setter
    @Getter
    class Person {
        private long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
/*
    코드를 보면 처음 접하는 Model이라는 것이 있습니다. 모델 객체는 뷰, 즉 HTML 쪽으로 값을 넘겨주는 객체입니다. 모델 객체는 따로 생성할
    필요 없이 코드처럼 argument로 선언하기만 하면 스프링이 알아서 만들어주므로 편리하게 사용할 수 있습니다. 여기서는 "person"이라는 키에
    사람 정보를, "today"라는 키에 날짜 정보를 저장합니다. thymeleafExample()이라는 메서드가 반환하는 값은 "example"인데, 이 값은
    클래스에 붙은 애너테이션이 @Controller이므로 뷰의 이름을 반환하는 겁니다. 즉, 스프링 부트는 컨트롤러의 @Controller 애너테이션을 보고
    '반환하는 값의 이름을 가진 뷰의 파일을 찾으라는 것이구나!'라고 이해하고 resources/templates 디렉토리에서 example.html을 찾은 다음
    웹 브라우저에 해당 파일을 보여줍니다.

        모델의 역할 살펴 보기

            이제 모델에는 "person", "today" 이렇게 두 키를 가진 데이터가 들어 있을 겁니다. 컨트롤러는 이렇게 모델을 통해 데이터를 설정하고,
            모델은 뷰로 이 데이터를 전달해 키에 맞는 데이터를 뷰에서 조회할 수 있게 해줍니다. 모델은 컨트롤러와 뷰의 중간다리 역할을 해준다고
            생각하시면 됩니다.
                                                        "person" id: 1
                                                                 name: "홍길동              뷰에서 사용할 수 있게
                                컨트롤러에서 데이터 설정             age : 11                  데이터 전달
            컨트롤러(Controller)---------------------->           hobbies : ["운동", "독서"] -------------------> 뷰(View)
                                                            모델(Model)


        뷰 작성하기

            01 단계 - src/main/resources/templates 디렉토리에 example.html 파일을 생성하세요.
                생성하고 코드 따라 작성하세요.

    2. 블로그 글 목록 뷰 구현하기
        이제 본격적인 뷰 구현을 할 예정입니다. 컨트롤러의 메서드를 만들고, HTML 뷰를 만든 다음 뷰를 테스트 합니다.

        컨트롤러 메서드 작성하기
            요청을 받아 사용자에게 뷰를 보여주려면 뷰 컨트롤러가 필요합니다. chapter06에서는 API를 만들기 위해 컨트롤러 메서드가 데이터를
            직렬화한 JSON 문자열을 반환했지만 뷰 컨트롤러 메서드는 뷰의 이름을 반환하고, 모델 객체에 값을 담습니다. 반화하는 값이 다를 뿐
            전체적인 구조는 비슷합니다.

                01 단계 - 뷰에게 데이터를 전달하기 위한 객체를 생성하겠습니다. dto 패키지에 ArticleListViewResonpse.java 파일을 만드세요.
 */
