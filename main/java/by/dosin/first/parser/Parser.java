package by.dosin.first.parser;

import by.dosin.first.entity.IntArray;
import by.dosin.first.exception.ArrayAppException;

import java.util.List;


public interface Parser {

    String DELIMITER_REGEX = "[,\\s;\\-]+";

    List<IntArray> parseLines(List<String> lines) throws ArrayAppException;

    IntArray parseLine(String line) throws ArrayAppException;
}