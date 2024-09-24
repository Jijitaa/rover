# ROVER
# Overview
The Rover Project simulates the movement of multiple rovers on a rectangular plateau on Mars. The rovers are controlled via a set of instructions and are required to navigate the plateau to capture images and send them back to Earth. The project reads input instructions from a file, processes the rover movements, and outputs the final positions and orientations of each rover.

# Input
The first line of input is the upper-right coordinates of the plateau, the
lower-left coordinates are assumed to be 0,0.
The rest of the input is information pertaining to the rovers that have
been deployed. Each rover has two lines of input. The first line gives the
rover's position, and the second line is a series of instructions telling
the rover how to explore the plateau.
The position is made up of two integers and a letter separated by spaces,
corresponding to the x and y co-ordinates and the rover's orientation.
Each rover will be finished sequentially, which means that the second rover
won't start to move until the first one has finished moving.

# Output
The output for each rover is its final co-ordinates and heading.

# Run
You can run the project using the command line:

java -jar rover.jar input.txt
