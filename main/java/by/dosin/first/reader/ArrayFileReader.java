package by.dosin.first.reader;

import by.dosin.first.exception.ArrayAppException;
import by.dosin.first.service.api.FileReaderService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ArrayFileReader {

    private static final String DATA_DIRECTORY = "data";

    public List<String> readLinesFromFile(String filename) throws ArrayAppException {

        Path filePath = Paths.get(DATA_DIRECTORY, filename);
        List<String> lines;

        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException exception) {
            throw new ArrayAppException("Ошибка чтения файла: " + filePath, exception);
        }

        if (lines != null) {
            return lines;
        } else {
            return Collections.emptyList();
        }
    }
}
