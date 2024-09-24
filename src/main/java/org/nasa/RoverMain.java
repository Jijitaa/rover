package org.nasa;

import org.nasa.entity.Rover;
import org.nasa.enumerator.DirectionEnum;
import org.nasa.service.MoveForwardStrategy;
import org.nasa.service.MovementStrategy;
import org.nasa.service.TurnLeftStrategy;
import org.nasa.service.TurnRightStrategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RoverMain {

    private static final String SPACE = " ";
    private static final Map<Character, MovementStrategy> strategies = new HashMap<>();
    private static final String INVALID_FILE_PATH_MSG = "You need to specify a valid file path";
    private static final String EMPTY_FILE_MSG = "The file must not be empty";

    public static void main(String[] args) {
        var input = readInput(args);

        if (!input.isEmpty()) {
            initializeStrategies();
            // Read plateau dimensions
            String[] dimensions = input.get(0).split(SPACE);
            int maxX = Integer.parseInt(dimensions[0]);
            int maxY = Integer.parseInt(dimensions[1]);

            for (int i = 1; i < input.size(); i += 2) {
                // Read rover's initial position and orientation
                String[] positionData = input.get(i).split(SPACE);
                var x = Integer.parseInt(positionData[0]);
                var y = Integer.parseInt(positionData[1]);
                var direction = positionData[2];
                var rover = new Rover(x, y, DirectionEnum.valueOf(direction));

                var instructions = input.get(i + 1);

                for (char instruction : instructions.toCharArray()) {
                    strategies.get(instruction).move(rover, maxX, maxY);
                }

                // Output the rover's final position
                System.out.println(rover.getX() + SPACE + rover.getY() + SPACE + rover.getDirection());
            }
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

    private static void initializeStrategies() {
        strategies.put('M', new MoveForwardStrategy());
        strategies.put('L', new TurnLeftStrategy());
        strategies.put('R', new TurnRightStrategy());
    }
}