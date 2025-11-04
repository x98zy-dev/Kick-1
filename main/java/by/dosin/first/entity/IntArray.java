package by.dosin.first.entity;

import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.observer.ArrayObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;

public class IntArray {
    private final String id;
    private int[] array;
    private final List<ArrayObserver> observers;

    public IntArray(String id, int[] array) throws ArrayAppException {
        if (id == null || id.isBlank()) {
            throw new ArrayAppException("ID cannot be null or empty");
        }
        if (array == null) {
            throw new ArrayAppException("Array cannot be null");
        }
        this.id = id;
        this.array = array.clone();
        this.observers = new ArrayList<>();
    }

    public void attach(ArrayObserver observer) {
        observers.add(observer);
    }

    public void detach(ArrayObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ArrayObserver observer : observers) {
            observer.onArrayChanged(this);
        }
    }

    public void setArray(int[] array) throws ArrayAppException {
        if (array == null) {
            throw new ArrayAppException("Array cannot be null");
        }
        this.array = array.clone();
        notifyObservers();
    }

    public String getId() {
        return id;
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
        sb.append("IntArray{");
        sb.append("id='").append(id).append('\'');
        sb.append(", array=").append(Arrays.toString(array));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArray intArray = (IntArray) o;
        return id.equals(intArray.id) && Arrays.equals(array, intArray.array);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Arrays.hashCode(array));
    }
}