package by.dosin.first.repository.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.repository.Specification;

import java.util.function.Predicate;

public class IdSpecification implements Specification {
    private final String id;

    public IdSpecification(String id) {
        this.id = id;
    }

    @Override
    public Predicate<IntArray> toPredicate() {
        return array -> array.getId().equals(id);
    }
}