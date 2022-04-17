package Chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

/** 특정 달엔 몇 건의 입출금 내역이 발생했는가?
 * -> 이전 코드 복사, 붙여넣기 + 주어진 월을 선택하도록 로직 변경
 *
 * 코드 유지보수성. 코드가 가졌으면 하는 속성들
 * 1. 특정 기능을 담당하는 코드를 쉽게 찾을 수 있어야 함
 * 2. 코드가 어떤 일을 수행하는지 쉽게 이해할 수 있어야 함
 * 3. 새로운 기능을 쉽게 추가하거나 기존 기능을 쉽게 제거할 수 있어야 함
 * 4. 캡슐화가 잘 되어 있어야 함. 즉 코드 사용자에게는 세부 구현 내용이 감춰져 있으므로
 *    사용자가 쉽게 코드를 이용하고 기능을 바꿀 수 있어야 함
 *
 * 궁극적으로 개발자의 목표는 현재 만들고 있는 응용프로그램의 복잡성을 관리하는 것.
 * 하지만 새로운 요구 사항이 생길 때마다 복사, 붙여넣기로 이를 해결한다면 다음과 같은 문제 발생.
 * 이는 효과적이지 않은 해결 방법으로, 안티 패턴이라고 불림.
 *
 * 1. 한 개의 거대한 갓 클래스(god class) 때문에 코드를 이해하기가 어려움
 * -> 한 개의 파일에 모든 코드를 구현하다 보면 결국 하나의 거대한 클래스가 탄생하면서 클래스의
 * 목적이 무엇인지 이해하기 어려워짐. 기존 코드의 로직을 갱신해야 한다면, 어떻게 이 코드를 찾아서
 * 바꿀 수 있을까? 이런 문제를 갓 클래스 안티 패턴이라 부른다. (한 클래스로 모든 것을 해결하는 패턴)
 * => 단일 책임 원칙
 *
 * 2. 코드 중복 때문에 코드가 불안정하고 변화에 쉽게 망가진다
 * -> 각 문제에서 입력을 읽고 파싱하는 로직이 중복됨. text 대신 json 파일로 입력 형식이 바뀐다면
 * 어떻게 될까? 또는 다양한 형식의 파일을 지원해야 한다면 어떨까? 현재 구현은 한 가지 문제만 해결하도록
 * 하드코딩되어 있고, 여러 곳에 이 코드가 중복되어 있어 기존의 기능을 바꾸기가 어려움. 결과적으로
 * 모든 곳의 코드를 다 바꿔야 하며, 새로운 버그가 발생할 가능성이 커짐.
 *
 * 코드를 간결하게 유지하는 것도 중요하지만 KISS 원칙을 남용하지 않아야 함.
 * 전체 응용 프로그램의 설계를 되돌아보고, 한 문제를 작은 개별 문제로 분리해 더 쉽게 관리할 수 있는지
 * 파악해야 한다.
 */
public class BankTransactionAnalyzer_2 {
    private static final String RESOURCES = "Real_World_Software_Development/src/main/resources/";

    public static void main(final String... args) throws IOException {
        // txt 파일을 응용 프로그램의 명령줄 인수로 전달해 로딩(귀찮아서 그냥 하드 코딩으로 넘김)
        final Path path = Paths.get(RESOURCES + "bank_data.txt");
        // 행 목록 반환
        final List<String> lines = Files.readAllLines(path);

        double total = 0d;
        // 일-월-연을 구분하기 위한 패턴
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for(final String line: lines) {
            // comma로 열 분리
            final String[] columns = line.split(",");
            // 패턴으로 날짜 파싱
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            // 1월 검색
            if(date.getMonth() == Month.JANUARY) {
                // 금액 추출 후 double로 파싱
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }
        System.out.println("The total for all transactions in January is "+total);
    }
}
