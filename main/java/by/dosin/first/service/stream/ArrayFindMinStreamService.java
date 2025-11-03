package by.dosin.first.service.stream;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.service.api.FindMinService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArrayFindMinStreamService implements FindMinService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayFindMinStreamService.class);

    @Override
    public int findMin(IntArray array) throws ArrayAppException {
        LOGGER.debug("Finding min value using Stream: {}", array);

        if (array.length() == 0) {
            LOGGER.error("Cannot find min in empty array");
            throw new ArrayAppException("Cannot find min in empty array");
        }

        int min = Arrays.stream(array.getArray())
                .min()
                .orElseThrow(() -> new ArrayAppException("Unexpected error finding min"));

        LOGGER.info("Min value found: {}", min);
        return min;
    }
}