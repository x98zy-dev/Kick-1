package by.dosin.first.service.api;

import by.dosin.first.entity.IntArray;

public interface ElementsReplaceService {
    IntArray replaceElements(IntArray array, int oldValue, int newValue);
}