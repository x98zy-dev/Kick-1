package by.dosin.first.service.stream;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.api.CountNegativeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArrayCountNegativeStreamService implements CountNegativeService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayCountNegativeStreamService.class);

    @Override
    public int countNegativeElements(IntArray array) {
        LOGGER.debug("Counting negative elements using Stream API: {}", array);

        long count = Arrays.stream(array.getArray())
                .filter(n -> n < 0)
                .count();

        LOGGER.info("Negative elements count: {}", count);
        return (int) count;
    }
}