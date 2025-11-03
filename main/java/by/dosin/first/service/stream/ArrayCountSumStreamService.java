package by.dosin.first.service.stream;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.api.CountSumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArrayCountSumStreamService implements CountSumService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayCountSumStreamService.class);

    @Override
    public int findSum(IntArray array) {
        LOGGER.debug("Calculating sum using Stream API: {}", array);

        int sum = Arrays.stream(array.getArray()).sum();

        LOGGER.info("Sum calculated: {}", sum);
        return sum;
    }
}