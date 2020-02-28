package stickman.model;

/** This is a concrete class implementing entity interface. */
public class entityClass implements Entity {
  protected double XPos;
  protected double YPos;
  protected double height;
  protected double width;
  protected Entity.Layer layer;
  protected String imagePath;

  public entityClass(
      String imagePath, double xPos, double YPos, double height, double width, Entity.Layer layer) {
    this.imagePath = imagePath;
    this.XPos = xPos;
    this.YPos = YPos;
    this.height = height;
    this.width = width;
    this.layer = layer;
  }

  public String getImagePath() {
    return imagePath;
  }

  /** Returns the entity's x-position on the level. */
  public double getXPos() {
    return XPos;
  }

  /** Returns the entity's x-position on the level. */
  public double getYPos() {
    return YPos;
  }

  public double getHeight() {
    return height;
  }

  public double getWidth() {
    return width;
  }

  /** Returns the entity's layer in the level. */
  public Entity.Layer getLayer() {
    return layer;
  }

  /** Entity moves left in the velocity of moveSpeed. */
  public void runLeft(double moveSpeed) {
    this.XPos -= moveSpeed;
  }

  /** Entity moves right in the velocity of moveSpeed. */
  public void runRight(double moveSpeed) {
    this.XPos += moveSpeed;
  }

  /** Entity jumps upwards, if applicable, with starting velocity of initialJumpSpeed. */
  public void jump(int initialJumpSpeed) {
    this.YPos -= initialJumpSpeed;
  }
}
