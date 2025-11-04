package by.dosin.first.validator.impl;

import by.dosin.first.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayValidator implements Validator {

    private static final Logger LOGGER = LogManager.getLogger(ArrayValidator.class);

    @Override
    public boolean isValidLine(String line) {
        LOGGER.debug("Validating line: '{}'", line);

        if (line == null) {
            LOGGER.error("Line is null");
            return false;
        }

        if (line.isBlank()) {
            LOGGER.debug("Line is blank");
            return false;
        }

        String[] numbersString = line.split(DELIMITER_REGEX);

        for(String numStr : numbersString) {
            String trimmed = numStr.trim();

            if (!trimmed.isBlank() && !trimmed.matches(NUMBER_REGEX)) {
                {
                    LOGGER.warn("Invalid number format: '{}'", trimmed);
                    return false;
                }
            }
        }

        LOGGER.debug("Line validation successful: '{}'", line);
        return true;
    }
}