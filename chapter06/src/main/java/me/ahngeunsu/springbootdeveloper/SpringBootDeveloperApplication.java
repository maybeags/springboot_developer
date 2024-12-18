package me.ahngeunsu.springbootdeveloper;
/*
    1. 사전 지식 : API와 REST API
        API : 프로그램 간에 상호작용하기 위한 매개체

        식당으로 알아보는 API
            식당에 들어가면 점원에게 요리를 주문합니다(주방으로 달려가서 요리를 주문 하는게 아니라)
            그리고 점원은 주방에 가서 요리를 만들어달라고 요청합니다. 즉,
                  요청        요청
            손님 <----> 점원 <----> 주방
                  응답        응답
            의 형태를 띄고 있는데, 여기서 손님은 클라이언트, 주방에서 일하는 요리사를 서버라고 생각하면 됩니다.
            그리고 중간에 있는 점원을 API라고 보시면 됩니다. -> 매개체라고 한 점 기억하세요.

            우리는 웹 사이트의 주소를 입력해서 '구글 메인 화면을 보여줘'라고 요청하면, API는 이 요청을 받아서 서버에게 가져다줍니다.
            그러면 서버는 API가 준 요청을 처리해 결과물을 만들고 이것을 다시 API로 전달하고, API는 최종 결과물을 브라우저에 보내주면
            여러분은 화면을 볼 수 있게 됩니다. 이처럼 API는 클라이언트의 요청을 서버에 잘 전달하고, 서버의 결과물을 클라이언트에게 잘
            돌려주는 역할을 합니다. 그렇다면 REST API란?

        웹의 장점을 최대한 활용하는 REST API
            Representational State Transfer를 줄인 표현으로, 자원을 이름으로 구분해 자원의 상태를 주고 받는 API 방식으로
            URL의 설계 방식을 의미함.

            REST API의 특징
                REST API는 서버/클라이언트 구조, 무상태, 캐시 처리 가능, 계층화, 일관성과 같은 특징이 있습니다. 추후 자세히 설명

            REST API의 장단점
                장점 :
                    URL만 보고도 무슨 행동을 하는 API인지 명확하게 알 수 있음.
                    무상태 특징으로 인해 클라이언트와 서버의 역할이 명확하게 분ㄹ리됨.
                    HTTP 표준을 사용하는 모든 플랫폼에서 사용 가능

                단점 :
                    HTTP 메서드, 즉 GET, POST와 같은 방식의 개수에 제한이 있음.
                    설계를 위해 공식적으로 제공되는 표준 규약이 없음.

                그럼에도 주소와 메서드만 보고 요청의 내용을 파악할 수 있다는 장점으로 많은 개발자들이 사용하고,
                REST하게 디자인한 API를 보고 RESTful API라고 부르기도 하는 편.

                REST API를 사용하는 방법
                    규칙 1. URL에는 동사를 쓰지 말고, 자원을 표시해야 한다.
                        자원 - 가져오는 데이터를 의미. 예를 들어 학생 중에 id가 1인 학생의 정보를 가져오는 URL은
                        -   /students/1
                        -   /get-student?student_id=1
                        과 같은 방식으로 설계할 수 있음.

                        이 중 더 REST API 맞는 API는 첫번째 것에 해당함. 왜냐하면 2 번의 경우 자원이 아닌 다른 표현을 섞어 사용함.
                        (get) 2번의 경우 동사를 사용해서 사용했기 때문에 추후 생길 수 있는 문제점의 예시로 서버에서 데이터를 요청하는
                        URL을 설계할 때 어떤 개발자는 get을, 다른 개발자는 show를 쓰면 get-student, show-data 등으로 엉망이 됨.
                        그렇기 때문에 '기능/행위'에 해당하지만 RESTful API에서는 이런 동사를 사용하지 않습니다.

                    규칙 2. 동사는 HTTP 메서드로


 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}
