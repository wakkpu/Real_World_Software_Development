package item10;

import java.awt.*;
import java.util.Objects;

public class ColorPointFin {
    private final Point point;
    private final Color color;

    public ColorPointFin(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof ColorPointFin)) return false;

        ColorPointFin cp = (ColorPointFin) o;
        return cp.point.equals(this.point) && cp.color.equals(this.color);
    }
}
