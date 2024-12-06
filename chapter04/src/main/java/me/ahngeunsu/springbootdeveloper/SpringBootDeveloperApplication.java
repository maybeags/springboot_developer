package me.ahngeunsu.springbootdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
    1. 테스트 코드 개념 익히기
        테스트 코드는 작성한 코드가 의도대로 잘 동작하고 예상치 못한 문제가 없는지 확인할 목적으로 작성하는 코드
        보통 테스트 코드 관련 공부는 본 개발 공부를 하느라 미루는 경우가 많으나 많은 장점을 가지고 있음

        장점 :
            1) 유지 보수에 편리함
            2) 코드 수정 시 기존 기능이 제대로 작동하지 않을까봐 걱정할 필요성이 없음

        테스트 코드란?
            테스트 코드는 test 디렉토리에서 작업함. -> 프로젝트 생성시 src의 하위 폴더, main과 같은 라인에 test 폴더가 있음.
            테스트 코드에는 다양한 패턴이 있으나 여기서 소개할 패턴은 given-when-then 패턴
                1) given : 테스트 실행을 준비하는 단계
                2) when : 테스트를 진행하는 단계
                3) then : 테스트 결과를 검증하는 단계

            예를 들어 새로운 메뉴를 저장하는 코드를 테스트한다고 가정했을 때 테스트 코드를 given, when, then으로 구분해 구현함

            예시

            @DisplayName("새로운 메뉴를 저장한다.")
            @Test
            public void saveMenuTest() {
                // 1. given : 메뉴를 저장하기 위한 준비 과정
                final String name = "아메리카노";
                final int price = 2000;
                final Menu americano = new Menu(name, price);

                // 2. when : 실제로 메뉴를 저장
                final long savedId = MenuService.save(americano);

                // 3. then : 메뉴가 잘 추가되었는지 검증
                final Menu savedMenu = menuService.findById(savedId).get();
                assertThat(savedMenu.getName()).isEqualTo(name);
                assertThat(SavedMenu.getPrice()).isEqualTo(price);
            }

        이상의 코드를 확인하면 세 부분으로 나뉘어져 있음. 메뉴를 저장하기 위해 준비하는 과정인 given절,
        실제로 메뉴를 저장하는 when절
        메뉴가 잘 추가되었는지 확인하는 then절로 나뉘어져 있음을 확인할 수 있음.

    2. 스프링 부트 3와 테스트
        스프링 부트는 애플리케이션을 테스트하기 위한 도구와 애너테이션을 제공함.
        spring-boot-starter-test 스타터에 테스트를 위한 도구가 모여있음

        스프링 부트 스타터 테스트 목록

            1) JUnit : 자바 프로그래밍 언어용 단위 테스트 프레임워크
            2) Spring Test & Spring Boot Test : 스프링 부트 애플리케이션을 위한 통합 테스트 지원
            3) AssertJ : 검증문인 어써션을 작성하는 데 사용되는 라이브러리
            4) Hamcrest : 표현식을 이해하기 쉽게 만드는 데 사용되는 Matcher 라이브러리
            5) Mockito : 테스트에 사용할 각짜 객체인 목 객체를 쉽게 만들고, 관리하고, 검증할 수 있게 지원하는 테스트 프레임 워크
            6) JSONassert : JSON 어써션 라이브러리
            7) JsonPath : JSON 데이터에서 특정 데이터를 선택하고 검색하기 위한 라이브러리

        이 중, JUnit과 AssertJ를 가장 많이 사용함.

            JUnit이란?
                자바 언어를 위한 *단위 테스트 프레임워크.
                    * 단위 테스트 : 작성한 코드가 의도대로 작동하는지 작은 단위로 검증하는 것을 의미
                        보통 단위는 메서드 기준
                JUnit을 이용하면 단위 테스트를 작성하고 테스트하는 데 도움을 줌. 테스트 결과가 직관적

                특징 :
                    1) 테스트 방식을 구분할 수 있는 애너테이션을 제공
                    2) @Test 애너테이션으로 메서드를 호출할 때마다 새 인스턴스를 생성, 독립 테스트도 가능
                    3) 예상 결과를 검증하는 assertion 메서드 제공
                    4) 사용 방법이 단순, 테스트 코드 작성 시간이 적음
                    5) 자동 실행, 자체 결과를 확인하고 즉각적인 피드백을 제공

                    JUnit으로 단위 테스트 코드 만들기
                        01 단계 - JUnitTest 파일 생성. src-test-java 폴더에 JUnitTest.java 파일 생성하고 코드 작성할 것
                        02 단계 = 실제로 테스트 코드가 잘 동작하는지 확인 -> JUnit 파일을 우클릭하여 테스트를 실행
                        성공 여부, 테스틐 케이스의 이름, 테스트 실행 시간 정보를 확인하기 위해서는 v 모양인 show passed 버튼 클릭 필요
                            * 한글이 깨지는 경우 Settings - Build, Execution, Deployment - Build Tools - Gradle에서
                            Build and run suing과 Run tests using을 Gradle에서 IDEA로 바꿀 필요 있음
                        03 단계 - 테스트를 실패했을 때의 예시 -> JUnitTest.java로 가서 코드 작성함.
                        04 단계 - JUnitCycleTest.java 파일 생성 및 코드 입력
                        05 단계 - 테스트 코드 실행해서 출력 결과 확인

                    AssertJ로 검증문 가독성 높이기

                        AssertJ는 JUnit과 함께 사용해 검증문의 가독성을 높여주는 라이브러리
                        예를 들어 이전에 학습한
                        Assertion.assertEquals(sum, a + b);를 떠올려보면 기대값과 비교값이 잘 구분되지 않음.

                        AssertJ를 사용한 예시
                        assertThat(a + b).isEqualTo(sum);
                        이 경우 a와 b를 더한 값이 sum과 같아야 한다는 의미로 읽힘 -> 영어의 경우

                        자주 사용하는 메서드 목록

                        1) isEqualTo(A) : A와 같은지 검증
                        2) isNotEqualTo(A) : A와 다른지 검증
                        3) contains(A) : A를 포함하는지 검증
                        4) doesnotContain(A) : A를 포함하지 않는지 검증
                        5) startsWith(A) : 접두사가 A인지 검증
                        6) endsWith(A) : 접미사가 A인지 검증
                        7) isEmpty() : 비어있는 값인지 검증
                        8) isNotEmpty() : 비어 있지 않은 값인지 검증
                        9) isPositive() : 양수인지 검증
                        10) isNegative() : 음수인지 검증
                        11) isGreaterThan(1) : 1보다 큰 값인지 검증
                        12) isLessThan(1) : 1보다 작은 값인지 검증

                    테스트 코드 작성 연습 문제 풀어보기 -> JUnitQuiz 파일을 test/java 폴더에 추가
 */
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeveloperApplication.class, args);
    }
}

