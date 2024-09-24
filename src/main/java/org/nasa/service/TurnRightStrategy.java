package org.nasa.service;

import org.nasa.entity.Rover;
import org.nasa.enumerator.DirectionEnum;

public class TurnRightStrategy implements MovementStrategy {

    @Override
    public void move(Rover rover, int maxX, int maxY) {
        switch (rover.getDirection()) {
            case N -> rover.setDirection(DirectionEnum.E);
            case E -> rover.setDirection(DirectionEnum.S);
            case W -> rover.setDirection(DirectionEnum.N);
            case S -> rover.setDirection(DirectionEnum.W);
        }
    }
}
