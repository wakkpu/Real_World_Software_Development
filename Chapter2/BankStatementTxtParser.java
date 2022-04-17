package Chapter2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 단일 책임 원칙(SRP)은 쉽게 관리하고 유지보수하는 코드를 구현하는 데 도움을 주는 포괄적인 소프트웨어 지침.
 * 1. 한 클래스는 한 기능만 책임진다.
 * 2. 클래스가 바뀌어야 하는 이유는 오직 하나여야 한다.
 *
 * SRP는 일반적으로 클래스와 메서드에 적용함. SRP는 한 가지 특정 동작, 개념, 카테고리와 관련됨.
 * SRP를 적용하면 코드가 바뀌어야 하는 이유가 한 가지로 제한되므로 더 튼튼한 코드를 만들 수 있음.
 * 이전 예제에서 살펴본 것처럼 코드가 바뀌는 이유가 한 가지가 아니라면, 여러 장소에서 코드 변경이
 * 발생하므로 코드 유지보수가 더 어려워진다. 또한 코드를 이해하고 바꾸기 어렵게 만드는 요인이기도 함.
 *
 * 그럼 Chapter2_2에 어떻게 SRP를 적용할까? 현재 메인 클래스는 여러 책임을 모두 포함하므로 이를
 * 개별로 분리해야 함.
 * 1. 입력 읽기
 * 2. 주어진 형식의 입력 파싱
 * 3. 결과 처리
 * 4. 결과 요약 리포트
 *
 * 첫 번째로, 다른 문제 구현에 이를 활용할 수 있도록 파싱 로직을 새로운 클래스로 분리한다.
 * 이 클래스는 ParseFromTxt()와 ParseLinesFromTxt()라는 BankTransaction 객체를 생성하는
 * 두 클래스를 정의한다. BankTransaction 클래스는 도메인 클래스로 입출금 내역을 표현함.
 */

public class BankStatementTxtParser {

    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private BankTransaction parseFromTxt(final String line) {
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFromTxt(final List<String> lines) {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for(final String line: lines) {
            bankTransactions.add(parseFromTxt(line));
        }
        return bankTransactions;
    }
}
