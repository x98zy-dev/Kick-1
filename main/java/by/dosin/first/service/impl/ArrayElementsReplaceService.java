package by.dosin.first.service.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.factory.IntArrayFactory;
import by.dosin.first.service.api.ElementsReplaceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayElementsReplaceService implements ElementsReplaceService {

    private static final Logger LOGGER = LogManager.getLogger(ArrayElementsReplaceService.class);

    @Override
    public IntArray replaceElements(IntArray array, int oldValue, int newValue) {
        LOGGER.debug("Replacing {} with {} in array: {}", oldValue, newValue, array);

        int[] elements = array.getArray();
        int[] result = elements.clone();
        int replacements = 0;

        for (int i = 0; i < result.length; i++) {
            if (result[i] == oldValue) {
                result[i] = newValue;
                replacements++;
                LOGGER.trace("Replaced element at index {}", i);
            }
        }

        LOGGER.info("Replacements completed: {} elements replaced", replacements);
        return IntArrayFactory.createFromArray(result);
    }
}