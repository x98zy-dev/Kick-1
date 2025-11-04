package by.dosin.first.queries.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.queries.Specification;

import java.util.function.Predicate;
import java.util.Arrays;

public class SumGreaterSpecification implements Specification {
    private final int threshold;

    public SumGreaterSpecification(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public Predicate<IntArray> toPredicate() {
        return array -> Arrays.stream(array.getArray()).sum() > threshold;
    }
}