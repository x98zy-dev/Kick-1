package by.dosin.first.validator;

public interface Validator {

    static final String DELIMITER_REGEX = "[,\\s;\\-]+";
    static final String NUMBER_REGEX = "-?\\d+";

    boolean isValidLine(String line);
}
