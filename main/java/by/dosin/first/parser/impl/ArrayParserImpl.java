package by.dosin.first.parser.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.factory.IntArrayFactory;
import by.dosin.first.parser.ArrayParser;
import by.dosin.first.validator.Validator;
import by.dosin.first.validator.impl.ArrayValidator;
import java.util.ArrayList;
import java.util.List;


public class ArrayParserImpl implements ArrayParser {

    private final Validator validator;

    public ArrayParserImpl() {
        this.validator = new ArrayValidator();
    }

    public ArrayParserImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public List<IntArray> parseLines(List<String> lines) throws ArrayAppException {
        if (lines == null) {
            throw new ArrayAppException("line can't be null");
        }

        List<IntArray> result = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line == null || line.isBlank()) {
                continue;
            }

            IntArray array = parseLine(line, "array_" + i);
            if (array != null) {
                result.add(array);
            }
        }

        return result;
    }

    @Override
    public IntArray parseLine(String line, String id) throws ArrayAppException {
        if (!validator.isValidLine(line)) {
            throw new ArrayAppException("incorrect data: " + line);
        }

        String[] numberStrings = line.split(DELIMITER_REGEX);
        List<Integer> numbers = new ArrayList<>();

        for (String num : numberStrings) {
            String trimmed = num.trim();
            if (!trimmed.isBlank()) {
                numbers.add(Integer.parseInt(trimmed));
            }
        }

        int[] array = numbers.stream().mapToInt(i -> i).toArray();
        return IntArrayFactory.create(id, array);
    }
}