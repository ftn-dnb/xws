package rs.ac.uns.ftn.xwsservice.utils;

import rs.ac.uns.ftn.xwsservice.exception.OperationFailedException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

    public static String readFromFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new OperationFailedException("Error while reading data from the file: " + filePath);
        }

        return contentBuilder.toString();
    }
}
