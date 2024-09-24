package org.nasa.service;

import org.nasa.entity.Rover;

public interface MovementStrategy {
    void move(Rover rover, int maxX, int maxY);
}
