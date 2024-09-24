package org.nasa.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the RoverService
 */
public class RoverServiceTest {

    private final RoverService roverService = new RoverService();

    @Test
    public void should_move_rover_and_display_final_position() {
        // Given
        var input = List.of("5 5", "1 2 N", "LMLMLMLMM");
        var byteArrayOutputStream = new ByteArrayOutputStream();
        var out = new PrintStream(byteArrayOutputStream);
        System.setOut(out);

        // When
        roverService.moveRoverAndDisplayFinalPosition(input);

        // Then
        var consoleOutput = byteArrayOutputStream.toString(Charset.defaultCharset());
        assertEquals("1 3 N\n", consoleOutput);
        out.close();
    }

    @ParameterizedTest
    @MethodSource("getInvalidPlateauCoordinates")
    public void should_throw_an_exception_when_plateau_coordinates_are_invalid(List<String> input, String expectedMessage) {
        // When
        var exception = assertThrows(IllegalArgumentException.class,
                () -> roverService.moveRoverAndDisplayFinalPosition(input));

        // Then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void should_throw_an_exception_when_instructions_are_invalid() {
        // When
        var exception = assertThrows(IllegalArgumentException.class,
                () -> roverService.moveRoverAndDisplayFinalPosition(List.of("5 5", "1 2 N", "ABC")));

        // Then
        assertEquals("Instructions must only contain the characters 'L', 'R', or 'M'", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getInvalidRoverPositions")
    public void should_throw_an_exception_when_rover_position_is_invalid(List<String> input, String expectedMessage) {
        // When
        var exception = assertThrows(IllegalArgumentException.class,
                () -> roverService.moveRoverAndDisplayFinalPosition(input));

        // Then
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> getInvalidPlateauCoordinates() {
        var arguments1 = Arguments.of(List.of("5 5 5", "1 2 3 N"), "Plateau coordinates must consist of two integers");
        var arguments2 = Arguments.of(List.of("A B", "1 Z N"), "Plateau coordinates must be integers");
        var arguments3 = Arguments.of(List.of("5 -1", "1 6 N"), "Plateau coordinates must be non-negative");
        return Stream.of(arguments1, arguments2, arguments3);
    }

    private static Stream<Arguments> getInvalidRoverPositions() {
        var arguments1 = Arguments.of(List.of("5 5", "1 2 3 N"), "Rover position must be in the format 'x y direction'");
        var arguments2 = Arguments.of(List.of("5 5", "1 Z N"), "Rover coordinates must be integers");
        var arguments3 = Arguments.of(List.of("5 5", "1 6 N"), "Rover position out of bounds");
        var arguments4 = Arguments.of(List.of("5 5", "-1 6 N"), "Rover position out of bounds");
        return Stream.of(arguments1, arguments2, arguments3, arguments4);
    }
}
