package by.dosin.first.factory;

import by.dosin.first.entity.IntArray;

public class IntArrayFactory {
    private IntArrayFactory() {}

    public static IntArray create(String id, int[] array) {
        return new IntArray(id, array);
    }

    public static IntArray createEmpty(String id) {
        return new IntArray(id, new int[0]);
    }

    public static IntArray createWithValues(String id, int... values) {
        return new IntArray(id, values);
    }
}