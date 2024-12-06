import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("1+2는 3이다.")   // 테스트 이름
    @Test   // 테스트 메서드
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum, a+b); // 값이 같은지 확인
    }

    // 03 단계의 실패한 코드 예씨
    @DisplayName("1 + 3은 4이다.")
    @Test
    public void junitFailedTest() {
    int a = 1;
    int b = 3;
    int sum = 3;

    Assertions.assertEquals(sum, a + b);
    // 이후 주석 처리
    }
}
/*
    @DisplayName 애너테이션 : 테스트 이름 명시
    @Test 애너테이션을 붙인 메서드는 테스트를 수행하는 메서드가 됨. JUnit은 테스트끼리 영향을 주지 않도록
        각 테스트를 실행할 때마다 테스트를 위한 실행 객체를 만들고 테스트가 종료되면 실행 객체를 삭제합니다.
    .assertEquals() 메서드 : 첫 번째 인수에는 기대하는 값, 두 번째 인수에는 실제로 검증할 값을 argument로 받음
 */
