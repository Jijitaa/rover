package org.nasa.service;

import org.nasa.entity.Rover;
import org.nasa.enumerator.DirectionEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoverService {

    private static final String SPACE = " ";
    private static final Map<Character, MovementStrategy> strategies = new HashMap<>();

    public void moveRoverAndDisplayFinalPosition(List<String> input) {
        initializeStrategies();
        // Read plateau dimensions
        int[] dimensions = readAndValidatePlateauCoordinates(input.get(0));

        for (int i = 1; i < input.size(); i += 2) {
            // Read rover's initial position and orientation
            var rover = readAndValidateRover(input.get(i), dimensions[0], dimensions[1]);

            // Read instructions
            var instructions = readAndValidateInstructions(input.get(i + 1));

            for (char instruction : instructions.toCharArray()) {
                strategies.get(instruction).move(rover, dimensions[0], dimensions[1]);
            }

            // Output the rover's final position
            System.out.println(rover.getX() + SPACE + rover.getY() + SPACE + rover.getDirection());
        }
    }
    private static void initializeStrategies() {
        strategies.put('M', new MoveForwardStrategy());
        strategies.put('L', new TurnLeftStrategy());
        strategies.put('R', new TurnRightStrategy());
    }

    private static int[] readAndValidatePlateauCoordinates(String line) {
        String[] parts = line.split(SPACE);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Plateau coordinates must consist of two integers");
        }
        try {
            int maxX = Integer.parseInt(parts[0]);
            int maxY = Integer.parseInt(parts[1]);
            if (maxX < 0 || maxY < 0) {
                throw new IllegalArgumentException("Plateau coordinates must be non-negative");
            }
            return new int[]{maxX, maxY};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Plateau coordinates must be integers", e);
        }
    }

    private static Rover readAndValidateRover(String line, int maxX, int maxY) {
        String[] parts = line.split(SPACE);
        if (parts.length != 3) {
            throw new IllegalArgumentException("Rover position must be in the format 'x y direction'");
        }
        try {
            var x = Integer.parseInt(parts[0]);
            var y = Integer.parseInt(parts[1]);
            var direction = parts[2];

            if (x < 0 || x > maxX || y < 0 || y > maxY) {
                throw new IllegalArgumentException("Rover position out of bounds");
            }
            return new Rover(x, y, DirectionEnum.valueOf(direction));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Rover coordinates must be integers", e);
        }
    }

    private static String readAndValidateInstructions(String line) {
        if (!line.matches("[LRM]+")) {
            throw new IllegalArgumentException("Instructions must only contain the characters 'L', 'R', or 'M'");
        }
        return line;
    }
}
