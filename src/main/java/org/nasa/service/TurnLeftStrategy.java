package org.nasa.service;

import org.nasa.entity.Rover;
import org.nasa.enumerator.DirectionEnum;

public class TurnLeftStrategy implements MovementStrategy {

    @Override
    public void move(Rover rover, int maxX, int maxY) {
        switch (rover.getDirection()) {
            case N -> rover.setDirection(DirectionEnum.W);
            case E -> rover.setDirection(DirectionEnum.N);
            case W -> rover.setDirection(DirectionEnum.S);
            case S -> rover.setDirection(DirectionEnum.E);
        }
    }
}
