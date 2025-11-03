package by.dosin.first.repository;

import by.dosin.first.entity.IntArray;
import java.util.List;
import java.util.Optional;

public interface Repository {
    void add(IntArray array);
    void remove(String id);
    Optional<IntArray> getById(String id);
    List<IntArray> getAll();
    boolean contains(String id);
}