package by.dosin.first.validator;

import by.dosin.first.exception.ArrayAppException;

public interface Validator {

    String DELIMITER_REGEX = "[,\\s;\\-]+";
    String NUMBER_REGEX = "-?\\d+";

    boolean isValidLine(String line);
}
