package by.dosin.first.service.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.api.SumService;

public class ArraySum implements SumService {

    @Override
    public int findSum(IntArray array) {
        int sum = 0;
        for(int i : array.getArray())
        {
            sum += i;
        }
        return sum;
    }
}
