package by.dosin.first.repository;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.observer.impl.ArrayObserverImpl;
import by.dosin.first.queries.Specification;
import by.dosin.first.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayRepository {
    private static final Logger LOGGER = LogManager.getLogger(ArrayRepository.class);
    private static ArrayRepository instance;
    private final List<IntArray> storage;
    private final ArrayObserverImpl arrayObserver;

    private ArrayRepository() {
        this.storage = new ArrayList<>();
        this.arrayObserver = new ArrayObserverImpl();
        LOGGER.debug("ArrayRepository initialized");
    }

    public static ArrayRepository getInstance() {
        if (instance == null) {
            instance = new ArrayRepository();
        }
        return instance;
    }

    public void add(IntArray array) throws ArrayAppException {
        if (array == null) {
            throw new ArrayAppException("Array cannot be null");
        }
        if (contains(array.getId())) {
            throw new ArrayAppException("Array with id " + array.getId() + " already exists");
        }
        storage.add(array);
        array.attach(arrayObserver);
        arrayObserver.onArrayChanged(array);
    }

    public void removeById(String id) throws ArrayAppException {
        if (id == null || id.isBlank()) {
            throw new ArrayAppException("ID cannot be null or empty");
        }
        boolean removed = storage.removeIf(array -> {
            if (array.getId().equals(id)) {
                array.detach(arrayObserver);
                return true;
            }
            return false;
        });
        if (!removed) {
            throw new ArrayAppException("Array with id " + id + " not found");
        }
        Warehouse.getInstance().removeStats(id);
    }

    public List<IntArray> getAll() {
        return new ArrayList<>(storage);
    }

    public List<IntArray> query(Specification specification) {
        return storage.stream()
                .filter(specification.toPredicate())
                .collect(Collectors.toList());
    }

    public boolean contains(String id) throws ArrayAppException {
        if (id == null || id.isBlank()) {
            throw new ArrayAppException("ID cannot be null or empty");
        }
        return storage.stream().anyMatch(array -> array.getId().equals(id));
    }

    public void clear() {
        for (IntArray array : storage) {
            array.detach(arrayObserver);
        }
        storage.clear();
        Warehouse.getInstance().clear();
    }

    public int size() {
        return storage.size();
    }
}