To run the game, enter game settings into default.json file located in src/main/resources/. The location of the json file must not be changed. Beware of constraints on the variables, refer to the default.json file comments for constraints. 

If unaccepted variables are entered, the game will exit with a System.err.print message indicating the incorrect variable.

Cloud velocity should be positive, if it is negative, the game will still run but clouds will move towards the left and eventually off the screen.

To change the velocity that stickman jumps at, within levelClass, change the jumpInitialVelocity and how much it decreases in the tick() method.

The stickman cannot move off the screen towards the left hand side, instead, move towards the right to continue through the level.


Code style: https://google.github.io/styleguide/javaguide.html