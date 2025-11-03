package by.dosin.first.service.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.service.api.CountSumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayCountSumService implements CountSumService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayCountSumService.class);

    @Override
    public int findSum(IntArray array) {
        LOGGER.debug("Calculating sum for array: {}", array);

        int sum = 0;
        for(int i : array.getArray()) {
            sum += i;
        }

        LOGGER.info("Sum calculated: {}", sum);
        return sum;
    }
}