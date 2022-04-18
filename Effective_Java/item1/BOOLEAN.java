package item1;

import java.util.EnumSet;

public class BOOLEAN {
    public static final Boolean TRUE = new Boolean(true);
    public static final Boolean FALSE = new Boolean(false);

    public static Boolean valueOf(boolean b) {
        return b ? TRUE : FALSE;
    }

}
