package by.dosin.first.repository.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.repository.ArrayRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ArrayRepositoryImpl implements ArrayRepository {
    private static ArrayRepositoryImpl instance;
    private final ConcurrentMap<String, IntArray> storage;

    private ArrayRepositoryImpl() {
        this.storage = new ConcurrentHashMap<>();
    }

    public static synchronized ArrayRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new ArrayRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(IntArray array) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        storage.put(array.getId(), array);
    }

    @Override
    public void remove(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        storage.remove(id);
    }

    @Override
    public Optional<IntArray> getById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<IntArray> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean contains(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return storage.containsKey(id);
    }
}