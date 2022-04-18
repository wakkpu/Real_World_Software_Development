package item10;

import java.awt.*;
import java.util.Set;

public class ColorPoint extends Point {
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    /**
    equals를 그대로 두면 Point의 구현이 상속되어 color는 무시한 채로 비교를 하게 됨.
    equals 규약을 어긴 것은 아니지만 중요한 정보를 놓치게 되는 문제가 발생
    */
    /*@Override
    public boolean equals(Object o) {
        if(!(o instanceof ColorPoint)) return false;

        return super.equals(o) && ((ColorPoint) o).color == color;
    }*/

    /**
    equals를 그대로 두면 Point의 구현이 상속되어 color는 무시한 채로 비교를 하게 됨.
    equals 규약을 어긴 것은 아니지만 중요한 정보를 놓치게 되는 문제가 발생
    */
    /*@Override
    public boolean equals(Object o) {
        if(!(o instanceof Point)) return false;

        // o가 Point면 색상 제외하고 비교
        if(!(o instanceof ColorPoint_1)) return o.equals(this);

        // o가 ColorPoint면 색상까지 비교
        return super.equals(o) && ((ColorPoint_1) o).color == color;
    }*/

    /**
    구체 클래스를 확장해 새로운 값을 추가하면서 equals 규약을 만족시킬 방법은 존재하지 않는다.
    객체 지향적 추상화의 이점을 포기하지 않는 한은 말이다.

    이번 equals는 같은 구현 클래스의 객체와 비교할 때만 true를 반환한다. 하지만 Point의
    하위 클래스는 정의상 여전히 Point이므로 어디서든 Point로 활용될 수 있어야 하는데,
    그렇지 못하므로 실제로 사용할 수 없다. (리스코프 치환 원칙 위반)

     * 리스코프 치환 원칙
     어떤 타입에 있어 중요한 속성이라면 그 하위 타입에서도 마찬가지로 중요하다. 따라서 그
     타입의 모든 메서드가 하위 타입에서도 똑같이 잘 작동해야 한다.
     */
    @Override
    public boolean equals(Object o) {
        if(o == null || o.getClass() != this.getClass()) return false;

        Point p = (Point) o;
        return p.x == this.x && p.y == this.y;
    }

    /**
     * Set.of() -> JAVA 9부터 지원
     */
    /*private static final Set<Point> unitCircle = Set.of(
            new Point(1, 0), new Point(0, 1),
            new Point(-1, 0), new Point(0, -1)
    );

    public static boolean onUnitCircle(Point p) {
        return unitCircle.contains(p);
    }*/

    public static void main(String[] args) {
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1, 2, Color.RED);

        System.out.println(p.equals(cp)); // true
        System.out.println(cp.equals(p)); // false

        System.out.println("-----");
        ColorPoint p1 = new ColorPoint(1, 2, Color.BLUE);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.RED);

        // 추이성 만족 X. p1과 p2, p2와 p3 비교에서는 색상 무시했지만 p1과 p3 비교에서는 색상까지 고려했기 때문
        System.out.println(p1.equals(p2)); // true
        System.out.println(p2.equals(p3)); // true
        System.out.println(p1.equals(p3)); // false
    }
}
