package by.dosin.first.parser;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.parser.ArrayParser;
import by.dosin.first.parser.impl.ArrayParserImpl;
import by.dosin.first.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayParserImplTest {

    private ArrayParser parser;

    @BeforeEach
    void setUp() {
        parser = new ArrayParserImpl();
    }

    @Test
    void testParseLineWithCommas() throws ArrayAppException {
        IntArray result = parser.parseLine("1,2,3", "arr1");
        assertArrayEquals(new int[]{1, 2, 3}, result.getArray());
        assertEquals("arr1", result.getId());
    }

    @Test
    void testParseLineWithSpaces() throws ArrayAppException {
        IntArray result = parser.parseLine("1 2 3", "arr2");
        assertArrayEquals(new int[]{1, 2, 3}, result.getArray());
    }

    @Test
    void testParseLineWithSemicolons() throws ArrayAppException {
        IntArray result = parser.parseLine("1;2;3", "arr3");
        assertArrayEquals(new int[]{1, 2, 3}, result.getArray());
    }

    @Test
    void testParseLineWithMixedDelimiters() throws ArrayAppException {
        IntArray result = parser.parseLine("1, 2; 3 - 4", "arr4");
        assertArrayEquals(new int[]{1, 2, 3, 4}, result.getArray());
    }

    @Test
    void testParseLineWithNegativeNumbers() throws ArrayAppException {
        IntArray result = parser.parseLine("-1, 2, -3", "arr5");
        assertArrayEquals(new int[]{-1, 2, -3}, result.getArray());
    }

    @Test
    void testParseLineWithExtraSpaces() throws ArrayAppException {
        IntArray result = parser.parseLine("  1 ,  2  ,  3  ", "arr6");
        assertArrayEquals(new int[]{1, 2, 3}, result.getArray());
    }

    @Test
    void testParseLineInvalidDataThrowsException() {
        assertThrows(ArrayAppException.class, () -> {
            parser.parseLine("1, abc, 3", "arr7");
        });
    }

    @Test
    void testParseLinesMultipleValidLines() throws ArrayAppException {
        List<String> lines = List.of("1,2,3", "4,5,6", "7,8,9");
        List<IntArray> result = parser.parseLines(lines);

        assertEquals(3, result.size());
        assertEquals("array_0", result.get(0).getId());
        assertEquals("array_1", result.get(1).getId());
        assertEquals("array_2", result.get(2).getId());
        assertArrayEquals(new int[]{1, 2, 3}, result.get(0).getArray());
        assertArrayEquals(new int[]{4, 5, 6}, result.get(1).getArray());
        assertArrayEquals(new int[]{7, 8, 9}, result.get(2).getArray());
    }

    @Test
    void testParseLinesSkipsEmptyLines() throws ArrayAppException {
        List<String> lines = List.of("1,2,3", "", "   ", "4,5,6");
        List<IntArray> result = parser.parseLines(lines);

        assertEquals(2, result.size());
        assertEquals("array_0", result.get(0).getId());
        assertEquals("array_3", result.get(1).getId());
    }

    @Test
    void testParseLinesSkipsInvalidLines() throws ArrayAppException {
        List<String> lines = List.of("1,2,3", "4,x,6", "7,8,9");
        List<IntArray> result = parser.parseLines(lines);

        assertEquals(2, result.size());
        assertEquals("array_0", result.get(0).getId());
        assertEquals("array_2", result.get(1).getId());
    }

    @Test
    void testParseLinesEmptyList() throws ArrayAppException {
        List<String> lines = List.of();
        List<IntArray> result = parser.parseLines(lines);

        assertTrue(result.isEmpty());
    }

    @Test
    void testParseLinesNullInputThrowsException() {
        assertThrows(ArrayAppException.class, () -> {
            parser.parseLines(null);
        });
    }

    @Test
    void testParseLineNullIdThrowsException() {
        assertThrows(ArrayAppException.class, () -> {
            parser.parseLine("1,2,3", null);
        });
    }

    @Test
    void testParseLineBlankIdThrowsException() {
        assertThrows(ArrayAppException.class, () -> {
            parser.parseLine("1,2,3", "   ");
        });
    }

    @Test
    void testCustomValidator() throws ArrayAppException {
        Validator customValidator = new Validator() {
            @Override
            public boolean isValidLine(String line) {
                return line.equals("1,2,3");
            }
        };

        ArrayParser customParser = new ArrayParserImpl(customValidator);

        IntArray result1 = customParser.parseLine("1,2,3", "test1");
        assertArrayEquals(new int[]{1, 2, 3}, result1.getArray());

        assertThrows(ArrayAppException.class, () -> {
            customParser.parseLine("4,5,6", "test2");
        });
    }
}