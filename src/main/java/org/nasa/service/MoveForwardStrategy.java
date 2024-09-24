package org.nasa.service;

import org.nasa.entity.Rover;

public class MoveForwardStrategy implements MovementStrategy {

    @Override
    public void move(Rover rover, int maxX, int maxY) {
        switch (rover.getDirection()) {
            case N -> rover.setY(rover.getY() + 1);
            case E -> rover.setX(rover.getX() + 1);
            case S -> rover.setY(rover.getY() - 1);
            case W -> rover.setX(rover.getX() - 1);
        }
        if (rover.getX() < 0 || rover.getY() < 0 || rover.getX() > maxX || rover.getY() > maxY) {
            throw new IllegalArgumentException("Rover's position is outside the allowed grid limits");
        }
    }
}
