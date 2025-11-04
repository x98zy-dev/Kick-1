package by.dosin.first.observer.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.observer.ArrayObserver;
import by.dosin.first.warehouse.Warehouse;

public class ArrayObserverImpl implements ArrayObserver {
    private final Warehouse warehouse = Warehouse.getInstance();

    @Override
    public void onArrayChanged(IntArray array) {
        warehouse.onArrayChanged(array);
    }
}