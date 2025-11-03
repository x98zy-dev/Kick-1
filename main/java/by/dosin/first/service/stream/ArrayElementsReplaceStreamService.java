package by.dosin.first.service.stream;

import by.dosin.first.entity.IntArray;
import by.dosin.first.factory.IntArrayFactory;
import by.dosin.first.service.api.ElementsReplaceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArrayElementsReplaceStreamService implements ElementsReplaceService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayElementsReplaceStreamService.class);

    @Override
    public IntArray replaceElements(IntArray array, int oldValue, int newValue) {
        LOGGER.debug("Replacing {} with {} using Stream API: {}", oldValue, newValue, array);

        int[] result = Arrays.stream(array.getArray())
                .map(n -> n == oldValue ? newValue : n)
                .toArray();

        long replacements = Arrays.stream(array.getArray())
                .filter(n -> n == oldValue)
                .count();

        LOGGER.info("Replacements completed: {} elements replaced", replacements);
        return IntArrayFactory.createFromArray(result);
    }
}