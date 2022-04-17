package Chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/** 은행 입출금 내역의 총 수입과 총 지출은 각각 얼마인가?
 * -> KISS(Keep It Short and Simple) 원칙을 이용해 응용프로그램 코드를 한 개의 클래스로 구현
 * -> 아래 코드는 정상 실행되지만 여러 문제 발생 가능
 * 1. 파일이 비어있다면?
 * 2. 데이터에 문제가 있어서 금액을 파싱하지 못한다면?
 * 3. 행의 데이터가 완벽하지 않다면?
 *
 * 지역 변수나 필드를 final로 정의하기 때문에 이 변수에 값을 재할당할 수 없음.
 * final 사용에 따른 장단점이 모두 있으므로 final 사용 여부는 팀과 프로젝트에 따라 달라짐.
 * 코드에서 가능한 많은 변수를 final로 표시하면 어떤 객체의 상태가 바뀔 수 있고, 어떤 객체의
 * 상태가 바뀔 수 없는지 명확하게 구분할 수 있음.
 *
 * 하지만 final 키워드를 적용한다고 해서 객체가 바뀌지 못하도록 강요하는 것은 아님.
 * final 필드로 가리키는 객체라도 가변 상태를 포함하기 때문임. final로 인해 더 많은 코드가 추가되기도 함.
 * 그래서 어떤 팀에서는 메소드 파라미터에 final 필드를 포함시켜 이들 변수가 지역 변수도 아니며, 다시
 * 할당할 수 없음을 명시하기도 함.
 */
public class BankTransactionAnalyzer_1 {
    private static final String RESOURCES = "Real_World_Software_Development/src/main/resources/";

    public static void main(final String... args) throws IOException {
        // txt 파일을 응용 프로그램의 명령줄 인수로 전달해 로딩(귀찮아서 그냥 하드 코딩으로 넘김)
        final Path path = Paths.get(RESOURCES + "bank_data.txt");
        // 행 목록 반환
        final List<String> lines = Files.readAllLines(path);

        double total = 0d;
        for(final String line: lines) {
            // comma로 열 분리
            final String[] columns = line.split(",");
            // 금액 추출 후 double로 파싱
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }
        System.out.println("The total for all transactions is "+total);
    }
}
