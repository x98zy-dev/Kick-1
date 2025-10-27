package by.dosin.first.entity;

public class IntArray {

    private final int[] array;

    public IntArray(int[] array) {
        if(array == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }
        this.array = array.clone();
    }

    public int[] getArray() {
        return array.clone();
    }

    public int length() {
        return array.length;
    }

    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if(i < array.length - 1)
                sb.append(", ");
        }
        return sb.toString();
    }
}
