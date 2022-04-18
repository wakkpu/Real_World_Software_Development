package item10;

/**
 * 상위 클래스에는 없는 새로운 필드를 하위 클래스에 추가하는 상황을 생각해보자
 */
public class Point {
    final int x;
    final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public boolean equals(Object o) {
        if(!(o instanceof Point)) return false;

        Point p = (Point) o;

        return p.x == x && p.y == y;
    }
}
