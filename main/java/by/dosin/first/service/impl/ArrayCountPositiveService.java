package by.dosin.first.service.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.api.CountPositiveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayCountPositiveService implements CountPositiveService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayCountPositiveService.class);

    @Override
    public int countPositiveElements(IntArray array) {
        LOGGER.debug("Counting positive elements in array: {}", array);

        int[] elements = array.getArray();
        int count = 0;

        for (int element : elements) {
            if (element > 0) {
                count++;
            }
        }

        LOGGER.info("Positive elements count: {}", count);
        return count;
    }
}