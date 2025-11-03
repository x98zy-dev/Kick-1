package by.dosin.first.repository;

import by.dosin.first.entity.IntArray;
import java.util.function.Predicate;

public interface Specification {
    Predicate<IntArray> toPredicate();
}