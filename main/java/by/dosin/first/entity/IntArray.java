package by.dosin.first.entity;

import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.observer.ArrayObserver;
import by.dosin.first.observer.ObservableEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class IntArray implements ObservableEntity {
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

    @Override
    public void attach(ArrayObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(ArrayObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArray intArray = (IntArray) o;
        return id.equals(intArray.id) && Arrays.equals(array, intArray.array);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        return "IntArray{id='" + id + "', array=" + Arrays.toString(array) + "}";
    }
}