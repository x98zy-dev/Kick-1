package by.dosin.first.service.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.service.api.FindMaxService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayFindMaxService implements FindMaxService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayFindMaxService.class);

    @Override
    public int findMax(IntArray array) throws ArrayAppException {
        LOGGER.debug("Finding max value in array: {}", array);

        if (array.length() == 0) {
            LOGGER.error("Cannot find max in empty array");
            throw new ArrayAppException("Cannot find max in empty array");
        }

        int[] elements = array.getArray();
        int max = elements[0];

        for (int i = 1; i < elements.length; i++) {
            if (elements[i] > max) {
                max = elements[i];
                LOGGER.trace("New max found: {}", max);
            }
        }

        LOGGER.info("Max value found: {}", max);
        return max;
    }
}