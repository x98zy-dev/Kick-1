package by.dosin.first.service.api;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;

public interface FindMinService {
    int findMin(IntArray array) throws ArrayAppException;
}