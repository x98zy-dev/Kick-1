package by.dosin.first.service.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.api.MinService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayFindMin implements MinService {

    private final static Logger logger = LogManager.getLogger(ArrayFindMin.class);

    @Override
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
}
