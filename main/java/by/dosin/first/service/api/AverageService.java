package by.dosin.first.service.api;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;

public interface AverageService {

    double calculateAverage(IntArray array) throws ArrayAppException;

    int calculateSum(IntArray array);
}