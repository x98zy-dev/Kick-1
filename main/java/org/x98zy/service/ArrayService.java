package org.x98zy.service;

import org.x98zy.entity.IntArray;

public class ArrayService {

    public int findMin(IntArray array) {
        int min = array.getArray()[0];
        for(int i : array.getArray())
        {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

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
