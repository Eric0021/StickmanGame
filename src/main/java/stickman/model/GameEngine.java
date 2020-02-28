package stickman.model;

public interface GameEngine {
  Level getCurrentLevel();

  void startLevel();

  boolean jump();

  boolean moveLeft();

  boolean moveRight();

  boolean stopMoving();

  void tick();
}
