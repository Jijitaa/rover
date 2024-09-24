package org.nasa;

import org.nasa.service.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RoverMain {

    private static final RoverService roverService = new RoverService();

    private static final String INVALID_FILE_PATH_MSG = "You need to specify a valid file path";
    private static final String EMPTY_FILE_MSG = "The file must not be empty";

    public static void main(String[] args) {
        var input = readInput(args);
        if (!input.isEmpty()) {
            roverService.moveRoverAndDisplayFinalPosition(input);
        } else {
            throw new IllegalArgumentException(EMPTY_FILE_MSG);
        }
    }

    private static List<String> readInput(String[] args) {
        return Optional.ofNullable(args)
                .filter(a -> a.length > 0)
                .map(path -> {
                    try {
                        return Files.readAllLines(Path.of(args[0]));
                    } catch (IOException e) {
                        throw new IllegalArgumentException(INVALID_FILE_PATH_MSG);
                    }
                })
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_PATH_MSG));
    }
}