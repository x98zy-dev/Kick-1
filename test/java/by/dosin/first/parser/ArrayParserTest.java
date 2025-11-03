package by.dosin.first.parser;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.parser.impl.ArrayParser;
import by.dosin.first.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayParserTest {

    private ArrayParser parser;

    @BeforeEach
    void setUp() {
        parser = new ArrayParser();
    }

    @Test
    void testParseLineWithCommas() throws ArrayAppException {
        IntArray result = parser.parseLine("1,2,3");
        assertArrayEquals(new int[]{1, 2, 3}, result.getArray());
    }

    @Test
    void testParseLineWithSpaces() throws ArrayAppException {
        IntArray result = parser.parseLine("1 2 3");
        assertArrayEquals(new int[]{1, 2, 3}, result.getArray());
    }

    @Test
    void testParseLineWithMixedDelimiters() throws ArrayAppException {
        IntArray result = parser.parseLine("1, 2; 3 - 4");
        assertArrayEquals(new int[]{1, 2, 3, 4}, result.getArray());
    }

    @Test
    void testParseLineWithNegativeNumbers() throws ArrayAppException {
        IntArray result = parser.parseLine("-1, 2, -3");
        assertArrayEquals(new int[]{-1, 2, -3}, result.getArray());
    }

    @Test
    void testParseLineWithExtraSpaces() throws ArrayAppException {
        IntArray result = parser.parseLine("  1 ,  2  ,  3  ");
        assertArrayEquals(new int[]{1, 2, 3}, result.getArray());
    }

    @Test
    void testParseLineSingleNumber() throws ArrayAppException {
        IntArray result = parser.parseLine("5");
        assertArrayEquals(new int[]{5}, result.getArray());
    }

    @Test
    void testParseLineInvalidDataThrowsException() {
        ArrayAppException exception = assertThrows(ArrayAppException.class, () -> {
            parser.parseLine("1, abc, 3");
        });
        assertTrue(exception.getMessage().contains("incorrect data"));
    }

    @Test
    void testParseLinesMultipleValidLines() throws ArrayAppException {
        List<String> lines = List.of("1,2,3", "4,5,6", "7,8,9");
        List<IntArray> result = parser.parseLines(lines);

        assertEquals(3, result.size());
        assertArrayEquals(new int[]{1, 2, 3}, result.get(0).getArray());
        assertArrayEquals(new int[]{4, 5, 6}, result.get(1).getArray());
        assertArrayEquals(new int[]{7, 8, 9}, result.get(2).getArray());
    }

    @Test
    void testParseLinesSkipsEmptyLines() throws ArrayAppException {
        List<String> lines = List.of("1,2,3", "", "   ", "4,5,6");
        List<IntArray> result = parser.parseLines(lines);

        assertEquals(2, result.size());
        assertArrayEquals(new int[]{1, 2, 3}, result.get(0).getArray());
        assertArrayEquals(new int[]{4, 5, 6}, result.get(1).getArray());
    }

    @Test
    void testParseLinesSkipsNullLines() throws ArrayAppException {
        List<String> lines = new ArrayList<>();
        lines.add("1,2,3");
        lines.add(null);
        lines.add("4,5,6");

        List<IntArray> result = parser.parseLines(lines);

        assertEquals(2, result.size());
        assertArrayEquals(new int[]{1, 2, 3}, result.get(0).getArray());
        assertArrayEquals(new int[]{4, 5, 6}, result.get(1).getArray());
    }

    @Test
    void testParseLinesSkipsInvalidLines() throws ArrayAppException {
        List<String> lines = List.of("1,2,3", "4,x,6", "7,8,9");
        List<IntArray> result = parser.parseLines(lines);

        assertEquals(2, result.size());
        assertArrayEquals(new int[]{1, 2, 3}, result.get(0).getArray());
        assertArrayEquals(new int[]{7, 8, 9}, result.get(1).getArray());
    }

    @Test
    void testParseLinesEmptyList() throws ArrayAppException {
        List<String> lines = List.of();
        List<IntArray> result = parser.parseLines(lines);

        assertTrue(result.isEmpty());
    }

    @Test
    void testParseLinesAllInvalidReturnsEmpty() throws ArrayAppException {
        List<String> lines = List.of("a,b,c", "x,y,z");
        List<IntArray> result = parser.parseLines(lines);

        assertTrue(result.isEmpty());
    }

    @Test
    void testParseLinesNullInputThrowsException() {
        ArrayAppException exception = assertThrows(ArrayAppException.class, () -> {
            parser.parseLines(null);
        });
        assertTrue(exception.getMessage().contains("line can't be null"));
    }

    @Test
    void testCustomValidator() throws ArrayAppException {
        Validator customValidator = new Validator() {
            @Override
            public boolean isValidLine(String line) {
                return line.equals("1,2,3");
            }
        };

        ArrayParser customParser = new ArrayParser(customValidator);

        IntArray result1 = customParser.parseLine("1,2,3");
        assertArrayEquals(new int[]{1, 2, 3}, result1.getArray());

        assertThrows(ArrayAppException.class, () -> {
            customParser.parseLine("4,5,6");
        });
    }
}