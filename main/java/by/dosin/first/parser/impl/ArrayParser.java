package by.dosin.first.parser.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.factory.IntArrayFactory;
import by.dosin.first.parser.Parser;
import by.dosin.first.validator.Validator;
import by.dosin.first.validator.impl.ArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ArrayParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(ArrayParser.class);
    private final Validator validator;

    public ArrayParser() {
        this.validator = new ArrayValidator();
    }

    public ArrayParser(Validator validator) {
        this.validator = validator;
    }

    @Override
    public List<IntArray> parseLines(List<String> lines) throws ArrayAppException {
        LOGGER.debug("Parsing {} lines", lines.size());

        if(lines == null) {
            LOGGER.error("Input lines list is null");
            throw new ArrayAppException("line can't be null");
        }

        List<IntArray> result = new ArrayList<>();

        for(String line : lines) {
            if (line == null || line.isBlank()) {
                LOGGER.debug("Skipping empty or null line");
                continue;
            }

            IntArray array = parseLine(line);
            if (array != null) {
                result.add(array);
            }
        }

        LOGGER.info("Successfully parsed {} arrays from {} lines", result.size(), lines.size());
        return result;
    }

    @Override
    public IntArray parseLine(String line) throws ArrayAppException {
        LOGGER.debug("Parsing line: {}", line);

        if (!validator.isValidLine(line)) {
            LOGGER.warn("Invalid data format: {}", line);
            throw new ArrayAppException("incorrect data: " + line);
        }

        String[] numberStrings = line.split(DELIMITER_REGEX);
        List<Integer> numbers = new ArrayList<>();

        for(String num : numberStrings) {
            String trimmed = num.trim();
            if(!trimmed.isBlank()){
                numbers.add(Integer.parseInt(trimmed));
            }
        }

        int[] array = numbers.stream().mapToInt(i -> i).toArray();
        IntArray result = IntArrayFactory.createFromArray(array);

        LOGGER.debug("Successfully parsed array: {}", result);
        return result;
    }
}