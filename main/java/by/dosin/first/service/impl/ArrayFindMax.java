package by.dosin.first.service.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.api.MaxService;

public class ArrayFindMax implements MaxService {

    public int findMax(IntArray array) {
        int max = array.getArray()[0];
        for(int i : array.getArray())
        {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }
}
