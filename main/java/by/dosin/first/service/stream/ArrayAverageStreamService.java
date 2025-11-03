package by.dosin.first.service.stream;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.service.api.AverageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArrayAverageStreamService implements AverageService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayAverageStreamService.class);

    @Override
    public double calculateAverage(IntArray array) throws ArrayAppException {
        LOGGER.debug("Calculating average using Stream API: {}", array);

        if (array.length() == 0) {
            LOGGER.error("Cannot calculate average for empty array");
            throw new ArrayAppException("Cannot calculate average for empty array");
        }

        double average = Arrays.stream(array.getArray())
                .average()
                .orElseThrow(() -> new ArrayAppException("Unexpected error calculating average"));

        LOGGER.info("Average calculated: {}", average);
        return average;
    }

    @Override
    public int calculateSum(IntArray array) {
        LOGGER.debug("Calculating sum using Stream API: {}", array);

        int sum = Arrays.stream(array.getArray()).sum();

        LOGGER.debug("Sum calculated: {}", sum);
        return sum;
    }
}