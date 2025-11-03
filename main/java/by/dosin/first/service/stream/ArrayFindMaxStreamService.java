package by.dosin.first.service.stream;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.service.api.FindMaxService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArrayFindMaxStreamService implements FindMaxService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayFindMaxStreamService.class);

    @Override
    public int findMax(IntArray array) throws ArrayAppException {
        LOGGER.debug("Finding max value using Stream API: {}", array);

        if (array.length() == 0) {
            LOGGER.error("Cannot find max in empty array");
            throw new ArrayAppException("Cannot find max in empty array");
        }

        int max = Arrays.stream(array.getArray())
                .max()
                .orElseThrow(() -> new ArrayAppException("Unexpected error finding max"));

        LOGGER.info("Max value found: {}", max);
        return max;
    }
}