package item6;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();

        map.put("Hello", "World");

        Set<String> set1 = map.keySet();
        Set<String> set2 = map.keySet();

        set1.remove("Hello");

        System.out.println(set1.size()); // 0
        System.out.println(set2.size()); // 0
    }

}
