package org.nasa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nasa.enumerator.DirectionEnum;

@AllArgsConstructor
@Setter @Getter
public class Rover {

    private int x;

    private int y;

    private DirectionEnum direction;
}
