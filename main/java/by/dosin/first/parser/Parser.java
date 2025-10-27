package by.dosin.first.parser;

import by.dosin.first.entity.IntArray;

import java.util.List;


public interface Parser {

    String DELIMITER_REGEX = "[,\\s;\\-]+";

    List<IntArray> parseLines(List<String> lines);

    IntArray parseLine(String line);
}