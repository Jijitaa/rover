package service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.nasa.entity.Rover;
import org.nasa.enumerator.DirectionEnum;
import org.nasa.service.MovementStrategy;
import org.nasa.service.TurnLeftStrategy;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the TurnLeftStrategy
 */
public class TurnLeftStrategyTest {

    private static final MovementStrategy turnLeftStrategy = new TurnLeftStrategy();

    @ParameterizedTest
    @MethodSource("getRovers")
    public void should_turn_left(Rover actual, Rover expected) {
        // When
        turnLeftStrategy.move(actual, 6, 7);

        // Then
        assertEquals(expected.getX(), actual.getX());
        assertEquals(expected.getY(), actual.getY());
        assertEquals(expected.getDirection(), actual.getDirection());
    }

    private static Stream<Arguments> getRovers() {
        var rover1 = Arguments.of(new Rover(1, 2, DirectionEnum.N), new Rover(1, 2, DirectionEnum.W));
        var rover2 = Arguments.of(new Rover(0, 2, DirectionEnum.E), new Rover(0, 2, DirectionEnum.N));
        var rover3 = Arguments.of(new Rover(1, 4, DirectionEnum.S), new Rover(1, 4, DirectionEnum.E));
        var rover4 = Arguments.of(new Rover(1, 0, DirectionEnum.W), new Rover(1, 0, DirectionEnum.S));
        return Stream.of(rover1, rover2, rover3, rover4);
    }
}
