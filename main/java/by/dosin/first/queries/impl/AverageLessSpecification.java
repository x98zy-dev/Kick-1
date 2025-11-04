package by.dosin.first.queries.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.queries.Specification;

import java.util.function.Predicate;
import java.util.Arrays;

public class AverageLessSpecification implements Specification {
    private final double threshold;

    public AverageLessSpecification(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public Predicate<IntArray> toPredicate() {
        return array -> {
            int[] elements = array.getArray();
            if (elements.length == 0) return false;
            double average = Arrays.stream(elements).average().orElse(0);
            return average < threshold;
        };
    }
}