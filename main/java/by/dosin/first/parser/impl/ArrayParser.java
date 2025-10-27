package by.dosin.first.parser.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.factory.IntArrayFactory;
import by.dosin.first.parser.Parser;
import by.dosin.first.validator.Validator;
import by.dosin.first.validator.impl.ArrayValidator;

import java.util.ArrayList;
import java.util.List;

public class ArrayParser implements Parser {

    private final Validator validator;

    public ArrayParser() {
        this.validator = new ArrayValidator();
    }

    public ArrayParser(Validator validator) {
        this.validator = validator;
    }

    @Override
    public List<IntArray> parseLines(List<String> lines) {

        List<IntArray> result = new ArrayList<>();

        for(String line : lines) {
            if (line == null || line.isBlank()) {
                continue;
            }

            IntArray array = parseLine(line);
            if (array != null) {
                result.add(array);
            }
        }

        return result;
    }

    @Override
    public IntArray parseLine(String line) {
        boolean isValid = validator.isValidLine(line);

        if(isValid){

            String[] numberStrings = line.split(DELIMITER_REGEX);
            List<Integer> numbers = new ArrayList<>();

            for(String num : numberStrings)
            {
                String trimmed = num.trim();
                if(!trimmed.isBlank()){
                    numbers.add(Integer.parseInt(trimmed));
                }
            }
            int[] array = numbers.stream().mapToInt(i -> i).toArray();
            return IntArrayFactory.createFromArray(array);
        }
        else {
            return null;
        }

    }
}
