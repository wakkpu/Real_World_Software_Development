package item7;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if(size == 0) throw new EmptyStackException();
        /*
        Stack에서 꺼낸 객체를 gc가 회수하지 않게됨.
        인덱스가 size 미만인 원소들은 다시 참조할 일 없는데 계속 메모리 상에 남게 됨.
        return elements[--size];
        */
        Object result = elements[--size];
        elements[size] = null; // 참조 해제
        return result;
    }

    private void ensureCapacity() {
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2*size+1);
    }
}
