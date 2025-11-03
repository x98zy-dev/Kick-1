package by.dosin.first.service.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.api.CountNegativeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayCountNegativeService implements CountNegativeService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayCountNegativeService.class);

    @Override
    public int countNegativeElements(IntArray array) {
        LOGGER.debug("Counting negative elements in array: {}", array);

        int[] elements = array.getArray();
        int count = 0;

        for (int element : elements) {
            if (element < 0) {
                count++;
            }
        }

        LOGGER.info("Negative elements count: {}", count);
        return count;
    }
}