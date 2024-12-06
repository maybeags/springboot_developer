import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCycleQuiz {
    /*
        문제 03.
        각각의 테스트를 시작하기 전에 "Hello!"를 출력하는 메서드와 모든 테스트를 끝마치고 "Bye!"를 출력하는 메서드를
        추가해주세요. 다음 클래스가 있다고 가정해보겠습니다.
     */

    @Test
    public void junitQuiz3() {
        System.out.println("this is the first test.");
    }

    @Test
    public void junitQuiz4() {
        System.out.println("this is the second test.");
    }

    /*
        여기에서 JUnitCycleQuiz 클래스를 실행하면 콘솔에 다음과 같이 출력되려면 어떻게 해야 할까요?

        실행 예

        Hello!
        this is is the first test.
        Hello!
        this is the second test.
        Bye!
     */

    // 답
    @BeforeEach
    public void beforeEach() {
        System.out.println("Hello!");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Bye!");
    }

    // 이거 이후
    // 3. 제대로 테스트 코드 작성해보기
    // TestConroller.java 파일을 열고 클래스 이름 위에 마우스 커서를 놓고 클릭한 다음 alt + enter 누르면
    // create test가 나타남. 이거 누르고 ok 누르면 TestControllerTest.java 파일이 test/java 패키지 아래에 생성됨.
    /*
    1.	테스트할 클래스 열기:
	•	Controller.java 파일을 열어둡니다.
	2.	메뉴에서 테스트 생성:
	•	상단 메뉴에서 **Navigate > Test > Create New Test...**를 선택합니다.
	3.	테스트 설정 창에서 옵션 선택:
	•	나타나는 Create Test 창에서 다음을 설정합니다:
	•	Testing Library: 사용할 테스트 프레임워크를 선택 (예: JUnit 5).
	•	Test Class Name: 기본값은 ControllerTest입니다. 필요시 수정 가능합니다.
	•	Methods to Test: 테스트할 메서드를 선택하거나 전체 메서드를 선택.
	•	Directory: 기본 테스트 디렉토리 (test/java)로 설정.
	•	설정을 마쳤으면 **OK**를 클릭합니다.
	4.	테스트 파일 생성 완료:
	•	IntelliJ IDEA가 자동으로 지정된 디렉토리에 테스트 클래스를 생성하고, 필요한 메서드의 스텁(기본 코드)을 작성해줍니다.

이 과정을 통해 단축키 없이도 테스트 파일을 생성할 수 있습니다. 추가 설정이나 디렉토리 위치를 변경하고 싶으면 Project Structure 메뉴에서 설정을 조정할 수 있습니다. 😊
     */
}
