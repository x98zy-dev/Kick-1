package by.dosin.first.factory;

import by.dosin.first.entity.IntArray;

public class IntArrayFactory {

    public static final String EMPTY_ARRAY_MESSAGE = "Создан пустой массив";

    private IntArrayFactory() {

    }

    public static IntArray createEmpty() {
        return new IntArray(new int[0]);
    }

    public static IntArray createFromArray(int[] array) {
        if (array != null) {
            return new IntArray(array);
        }
        else {
           return createEmpty();
        }
    }
}
