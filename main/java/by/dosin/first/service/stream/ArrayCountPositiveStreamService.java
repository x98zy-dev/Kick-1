package by.dosin.first.service.stream;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.api.CountPositiveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArrayCountPositiveStreamService implements CountPositiveService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayCountPositiveStreamService.class);

    @Override
    public int countPositiveElements(IntArray array) {
        LOGGER.debug("Counting positive elements using Stream API: {}", array);

        long count = Arrays.stream(array.getArray())
                .filter(n -> n > 0)
                .count();

        LOGGER.info("Positive elements count: {}", count);
        return (int) count;
    }
}