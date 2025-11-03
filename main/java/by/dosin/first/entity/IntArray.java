package by.dosin.first.entity;

import java.util.Arrays;

public class IntArray {
    private final String id;
    private final String name;
    private int[] array;

    public IntArray(String id, String name, int[] array) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        this.id = id;
        this.name = name;
        this.array = array.clone();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int[] getArray() {
        return array.clone();
    }

    public void setArray(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        this.array = array.clone();
    }

    public int length() {
        return array.length;
    }

    @Override
    public String toString() {
        return "IntArray{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", array=" + Arrays.toString(array) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArray intArray = (IntArray) o;
        return id.equals(intArray.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}