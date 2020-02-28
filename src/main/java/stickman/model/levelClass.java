package stickman.model;

import java.util.List;

public class levelClass implements Level {
  private List<Entity> entities;
  private double height;
  private double width;
  private double floorHeight;
  private double cloudVelocity;
  private boolean left = false;
  private boolean right = false;
  private boolean midJump = false;
  private int jumpInitialVelocity = 10;
  private entityClass stickman;

  public levelClass(
      List<Entity> entities,
      double height,
      double width,
      double floorHeight,
      double cloudVelocity) {
    this.entities = entities;
    this.height = height;
    this.width = width;
    this.floorHeight = floorHeight;
    this.cloudVelocity = cloudVelocity;
  }

  public List<Entity> getEntities() {
    return entities;
  }

  public double getHeight() {
    return height;
  }

  public double getWidth() {
    return width;
  }

  /**
   * Allows and checks for movement of entities,
   * clouds will move at a constant rate regardless of key input, stickman will move at a constant
   * velocity of 1 in a direction that is dependent on key input, also implements stickman's ability
   * to jump.
   */
  public void tick() {
    for (Entity entity : entities) {
      if (entity.getImagePath().charAt(1) == 'c' && entity.getImagePath().charAt(2) == 'h') {
        stickman = (entityClass) entity;
      } else if (entity.getImagePath().contains("cloud_1")) {
        entity.runRight(cloudVelocity * 0.017);
      } else if (entity.getImagePath().contains("cloud_2")) {
        entity.runRight(cloudVelocity * 0.02);
      }
    }

    if (left) {
      if (stickman.getXPos() > 0) {
        stickman.runLeft(1);
      }
    }
    if (right) {
      stickman.runRight(1);
    }
    if (midJump) {
      stickman.jump(jumpInitialVelocity);
      jumpInitialVelocity -= 1;
      if (jumpInitialVelocity < -10) {
        midJump = false;
      }
    }
  }

  public double getFloorHeight() {
    return floorHeight;
  }

  public double getHeroX() {
    return stickman.getXPos();
  }

  /**
   * Implements jumping,
   * if stickman is not already jumping, it will reset the jump initial velocity. If stickman
   * is already jumping, returns false so as to not trigger audio feedback. Finally,
   * triggers jumping logic in tick().
   */
  public boolean jump() {
    if (!midJump) {
      jumpInitialVelocity = 10;
    } else {
      return false;
    }
    midJump = true;
    return true;
  }

  public boolean moveLeft() {
    left = true;
    return true;
  }

  public boolean moveRight() {
    right = true;
    return true;
  }

  public boolean stopMoving() {
    left = false;
    right = false;
    return true;
  }
}
