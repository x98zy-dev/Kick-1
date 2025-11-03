package by.dosin.first.factory;

import by.dosin.first.entity.IntArray;

public class IntArrayFactory {

    private IntArrayFactory() {
    }

    public static IntArray createWithIdAndName(String id, String name, int[] array) {
        return new IntArray(id, name, array);
    }

    public static IntArray createEmpty(String id, String name) {
        return new IntArray(id, name, new int[0]);
    }

    public static IntArray createFromArray(String id, String name, int[] array) {
        if (array != null) {
            return new IntArray(id, name, array);
        } else {
            return createEmpty(id, name);
        }
    }

    public static IntArray createWithValues(String id, String name, int... values) {
        return new IntArray(id, name, values);
    }
}