package by.dosin.first.validator;

import by.dosin.first.exception.ArrayAppException;

public interface Validator {

    static final String DELIMITER_REGEX = "[,\\s;\\-]+";
    static final String NUMBER_REGEX = "-?\\d+";

    boolean isValidLine(String line) throws ArrayAppException;
}
