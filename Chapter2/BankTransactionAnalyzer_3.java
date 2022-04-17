package Chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * parseLinesFromTxt() 메서드를 사용해 리팩토링.
 * BankTransaction 객체에서 직접 정보를 추출하기 때문에 내부 파싱 방법을 알 필요가 없어짐.
 *
 * 리팩토링 덕분에 메인 응용프로그램에서 파싱 로직을 구현하는 부분이 사라짐.
 * 대신 파싱 기능을 다른 클래스와 메서드에 위임했고, 이 기능을 독립적으로 구현했음.
 * 다양한 문제를 처리해야 하는 새 요구 사항이 들어오면, BankStatementTxtParser 클래스로 캡슐화된
 * 기능을 재사용해 구현함.
 *
 * 파싱 알고리즘 동작 방식(예를 들어 결과를 캐싱해서 효율성을 높임)을 바꿔야 하는 일이 생겨도
 * 한 곳의 코드만 바꾸면 된다. 게다가 BankTransaction 클래스 덕분에 다른 코드가 특정 데이터
 * 형식에 의존하지 않게 되었음.
 *
 * 메소드를 구현할 때는 놀람 최소화 원칙(어떤 메소드가 다른 메소드와 달리 예상치 못한 방법으로 동작한다면
 * 코드를 이해하기가 어려울 것이다. 따라서 누군가가 놀라지 않도록 일관성을 유지하는 범위에서 코드를 구현할
 * 것을 강조하는 원칙)을 따라야 한다. 그래야 코드를 보고 무슨 일이 일어나는지 명확히 이해할 수 있기 때문이다.
 * 1. 메소드가 수행하는 일을 바로 이해할 수 있도록 자체 문서화를 제공하는 메소드명을 사용한다.
 * 2. 코드의 다른 부분이 파라미터의 상태에 의존할 수 있으므로 파라미터의 상태를 바꾸지 않는다.
 *
 * 놀람 최소화 원칙은 다소 주관적인 개념. 확신이 서지 않는다면 동료나 팀원과 함께 의논하는 것이 좋다.
 */

public class BankTransactionAnalyzer_3 {
    private static final String RESOURCES = "Real_World_Software_Development/src/main/resources/";

    public static void main(final String... args) throws IOException {
        final BankStatementTxtParser bankStatementTxtParser = new BankStatementTxtParser();

        final String fileName = "bank_data.txt";
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementTxtParser.parseLinesFromTxt(lines);

        System.out.println("The total for all transactions is "+calculateTotalAmount(bankTransactions));
        System.out.println("Transactions in January is "+selectInMonth(bankTransactions, Month.JANUARY));
    }

    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
        double total = 0d;
        for(final BankTransaction bankTransaction: bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
        final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
        for(final BankTransaction bankTransaction: bankTransactions) {
            if(bankTransaction.getDate().getMonth() == month) {
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactions;
    }
}
