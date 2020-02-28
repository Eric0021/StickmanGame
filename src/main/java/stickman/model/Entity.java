package stickman.model;

/** Objects and entities in the level. */
public interface Entity {
  /** Returns the relative path of the entity's image. */
  String getImagePath();

  double getXPos();

  double getYPos();

  double getHeight();

  double getWidth();

  /** Returns the entity's layer in the level. */
  Layer getLayer();

  /** Entity moves left in the velocity of moveSpeed. */
  void runLeft(double moveSpeed);

  /** Entity moves right in the velocity of moveSpeed. */
  void runRight(double moveSpeed);

  /** Entity jumps upwards, if applicable, with starting velocity of initialJumpSpeed. */
  void jump(int initialJumpSpeed);

  enum Layer {
    BACKGROUND,
    FOREGROUND,
    EFFECT
  }
}
