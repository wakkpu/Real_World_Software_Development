package item10;

import java.util.Objects;

public class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    /*// 대칭성 위배
    @Override public boolean equals(Object o) {
        if(o instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);

        if(o instanceof String)
            return s.equalsIgnoreCase((String) o);

        return false;
    }*/

    // 이렇게 구현해야 함
    @Override public boolean equals(Object o) {
        return o instanceof CaseInsensitiveString &&
                ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
    }

    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "Polish";

        /*
        CaseInsensitiveString의 equals는 일반 String을 알고 있지만
        String의 equals는 CaseInsensitiveString을 모름
        -> equals 규약을 어기면 그 객체를 사용하는 다른 객체들이 어떻게 반응할지 알 수 없음
         */

        System.out.println(cis.equals(s));
        System.out.println(s.equals(cis));
    }
}
