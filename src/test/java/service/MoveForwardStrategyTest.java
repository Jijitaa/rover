package service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.nasa.entity.Rover;
import org.nasa.enumerator.DirectionEnum;
import org.nasa.service.MoveForwardStrategy;
import org.nasa.service.MovementStrategy;

import java.util.stream.Stream;

import static  org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the MoveForwardStrategy
 */
public class MoveForwardStrategyTest {

    private static final MovementStrategy moveForwardStrategy = new MoveForwardStrategy();

    @ParameterizedTest
    @MethodSource("getRovers")
    public void should_move_forward_depending_on_direction(Rover actual, Rover expected) {
        // When
        moveForwardStrategy.move(actual, 6, 7);

        // Then
        assertEquals(expected.getX(), actual.getX());
        assertEquals(expected.getY(), actual.getY());
        assertEquals(expected.getDirection(), actual.getDirection());
    }

    @ParameterizedTest
    @MethodSource("getInvalidRovers")
    public void should_throw_exception_when_grid_limits_are_exceeded(Rover rover) {
        // When
        var exception = assertThrows(IllegalArgumentException.class,
                () -> moveForwardStrategy.move(rover, 5, 5));

        // Then
        assertEquals("Rover's position is outside the allowed grid limits", exception.getMessage());
    }

    private static Stream<Arguments> getRovers() {
        var rover1 = Arguments.of(new Rover(1, 2, DirectionEnum.N), new Rover(1, 3, DirectionEnum.N));
        var rover2 = Arguments.of(new Rover(0, 2, DirectionEnum.E), new Rover(1, 2, DirectionEnum.E));
        var rover3 = Arguments.of(new Rover(1, 4, DirectionEnum.S), new Rover(1, 3, DirectionEnum.S));
        var rover4 = Arguments.of(new Rover(1, 0, DirectionEnum.W), new Rover(0, 0, DirectionEnum.W));
        return Stream.of(rover1, rover2, rover3, rover4);
    }

    private static Stream<Rover> getInvalidRovers() {
        var rover1 = new Rover(4, 5, DirectionEnum.N);
        var rover2 = new Rover(5, 2, DirectionEnum.E);
        var rover3 = new Rover(4, 0, DirectionEnum.S);
        var rover4 = new Rover(0, 3, DirectionEnum.W);
        return Stream.of(rover1, rover2, rover3, rover4);
    }
}
