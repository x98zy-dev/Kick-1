package by.dosin.first.reader;

import by.dosin.first.exception.ArrayAppException;
import java.util.List;

public interface ArrayFileReader {
    List<String> readLinesFromFile(String filename) throws ArrayAppException;
}