package by.dosin.first.service.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.service.api.FindMinService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayFindMinService implements FindMinService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayFindMinService.class);

    @Override
    public int findMin(IntArray array) throws ArrayAppException {
        LOGGER.debug("Finding min value in array: {}", array);

        if (array.length() == 0) {
            LOGGER.error("Cannot find min in empty array");
            throw new ArrayAppException("Cannot find min in empty array");
        }

        int[] elements = array.getArray();
        int min = elements[0];

        for (int i = 1; i < elements.length; i++) {
            if (elements[i] < min) {
                min = elements[i];
                LOGGER.trace("New min found: {}", min);
            }
        }

        LOGGER.info("Min value found: {}", min);
        return min;
    }
}