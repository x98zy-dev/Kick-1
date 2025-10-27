package by.dosin.first.validator.impl;

import by.dosin.first.validator.Validator;

public class ArrayValidator implements Validator {

    @Override
    public boolean isValidLine(String line) {
        if (line == null || line.isBlank()) {
            return false;
        }

        String[] numbersString = line.split(DELIMITER_REGEX);

        for(String numStr : numbersString) {
            String trimmed = numStr.trim();

            if (!trimmed.isBlank()) {
                if(!trimmed.matches(NUMBER_REGEX)) {
                    return false;
                }
            }
        }
        return true;
    }
}
