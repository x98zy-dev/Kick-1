package by.dosin.first.validator;

import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.validator.impl.ArrayValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayValidatorTest {

    private ArrayValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ArrayValidator();
    }

    @Test
    void testValidLinesWithCommas() throws ArrayAppException {
        assertTrue(validator.isValidLine("1,2,3"));
        assertTrue(validator.isValidLine("1, 2, 3"));
        assertTrue(validator.isValidLine("10,20,30"));
    }

    @Test
    void testValidLinesWithSemicolons() throws ArrayAppException {
        assertTrue(validator.isValidLine("1;2;3"));
        assertTrue(validator.isValidLine("1; 2; 3"));
    }

    @Test
    void testValidLinesWithSpaces() throws ArrayAppException {
        assertTrue(validator.isValidLine("1 2 3"));
        assertTrue(validator.isValidLine("1  2   3"));
    }

    @Test
    void testValidLinesWithDashes() throws ArrayAppException {
        assertTrue(validator.isValidLine("1-2-3"));
        assertTrue(validator.isValidLine("1 - 2 - 3"));
    }

    @Test
    void testValidLinesWithMixedDelimiters() throws ArrayAppException {
        assertTrue(validator.isValidLine("1, 2; 3 - 4"));
        assertTrue(validator.isValidLine("1;2,3-4"));
    }

    @Test
    void testValidLinesWithNegativeNumbers() throws ArrayAppException {
        assertTrue(validator.isValidLine("-1, -2, -3"));
        assertTrue(validator.isValidLine("1, -2, 3"));
    }

    @Test
    void testInvalidLinesWithLetters() throws ArrayAppException {
        assertFalse(validator.isValidLine("1, a, 3"));
        assertFalse(validator.isValidLine("abc, 2, 3"));
        assertFalse(validator.isValidLine("1, 2, 3x"));
    }

    @Test
    void testInvalidLinesWithSpecialCharacters() throws ArrayAppException {
        assertFalse(validator.isValidLine("1, 2.5, 3"));
        assertFalse(validator.isValidLine("1, 2!, 3"));
        assertFalse(validator.isValidLine("1, 2@, 3"));
    }

    @Test
    void testInvalidLinesWithEmptyNumbers() throws ArrayAppException {
        assertFalse(validator.isValidLine("1, , 3"));
        assertFalse(validator.isValidLine(",2,3"));
        assertFalse(validator.isValidLine("1,2,"));
    }

    @Test
    void testEmptyAndBlankLines() throws ArrayAppException {
        assertFalse(validator.isValidLine(""));
        assertFalse(validator.isValidLine("   "));
        assertFalse(validator.isValidLine("  \t  "));
    }

    @Test
    void testNullLineThrowsException() {
        ArrayAppException exception = assertThrows(ArrayAppException.class, () -> {
            validator.isValidLine(null);
        });

        assertTrue(exception.getMessage().contains("line can't be null"));
    }

    @Test
    void testLinesWithOnlyDelimiters() throws ArrayAppException {
        assertFalse(validator.isValidLine(","));
        assertFalse(validator.isValidLine(";"));
        assertFalse(validator.isValidLine("-"));
        assertFalse(validator.isValidLine(" , ; - "));
    }

    @Test
    void testSingleNumber() throws ArrayAppException {
        assertTrue(validator.isValidLine("5"));
        assertTrue(validator.isValidLine("-10"));
        assertTrue(validator.isValidLine("0"));
    }
}