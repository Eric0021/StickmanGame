package stickman.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class GameEngineImpl implements GameEngine {

  private int stickmanStartingYPos;
  private int stickmanSize;
  private double stickmanPosition;
  private double cloudVelocity;
  private levelClass currentLevel;
  private List<Entity> entities = new ArrayList<>();

  public GameEngineImpl(String JsonFile) {
    getJsonInfo(JsonFile);
    createEntities();
    startLevel();
  }

  /** Checks and retrieves Json data from default.json and converts them into the correct type. */
  public void getJsonInfo(String JsonFile) {
    JSONObject json;

    try {
      json = (JSONObject) new JSONParser().parse(new FileReader("src/main/resources/" + JsonFile));
    } catch (FileNotFoundException x) {
      return;
    } catch (IOException y) {
      return;
    } catch (ParseException z) {
      return;
    }

    JSONObject config = json;
    String stickmanSizeInput = "";
    if (config.get("stickmanSize") instanceof String) {
      stickmanSizeInput = (String) config.get("stickmanSize");
    } else {
      System.err.println("please input String into stickmanSize");
      exit(1);
    }
    JSONObject stickmanPositionJson = (JSONObject) config.get("stickmanPos");
    if (stickmanPositionJson.get("x") instanceof Double) {
      stickmanPosition = (double) stickmanPositionJson.get("x");
    } else {
      System.err.println("please enter a double for position of stickman");
      exit(1);
    }
    if (stickmanPosition < 0) {
      System.err.println("please enter a positive position for stickman");
      exit(1);
    }
    if (config.get("cloudVelocity") instanceof Double) {
      cloudVelocity = (double) config.get("cloudVelocity");
    } else {
      System.err.println("please enter a double for cloudVelocity");
      exit(1);
    }
    stickmanInputToSize(stickmanSizeInput);
    setStickmanStartingYPos();
  }

  public Level getCurrentLevel() {
    return currentLevel;
  }

  /** Creates new instance of level to start the game. */
  public void startLevel() {
    currentLevel = new levelClass(entities, 400, 500, 300, cloudVelocity);
  }

  /** Converts stickmanSize String into corresponding int sizes. */
  public void stickmanInputToSize(String size) {
    if (size.toLowerCase().equals("tiny")) {
      stickmanSize = 20;
    } else if (size.toLowerCase().equals("normal")) {
      stickmanSize = 50;
    } else if (size.toLowerCase().equals("large")) {
      stickmanSize = 70;
    } else if (size.toLowerCase().equals("giant")) {
      stickmanSize = 100;
    }
  }

  /**
   * Adjusts the starting Y position of stickman, so that stickman will always appear to be standing
   * on the ground.
   */
  public void setStickmanStartingYPos() {
    if (stickmanSize == 20) {
      stickmanStartingYPos = 280;
    } else if (stickmanSize == 50) {
      stickmanStartingYPos = 250;
    } else if (stickmanSize == 70) {
      stickmanStartingYPos = 230;
    } else if (stickmanSize == 100) {
      stickmanStartingYPos = 200;
    }
  }

  /** Creates entities for the current level. */
  public void createEntities() {
    String heroImage = "/ch_stand1.png";
    entityClass stickman =
        new entityClass(
            heroImage,
            stickmanPosition,
            stickmanStartingYPos,
            stickmanSize,
            stickmanSize,
            Entity.Layer.FOREGROUND);
    entities.add(stickman);

    String cloudImage = "/cloud_2.png";
    entityClass cloud = new entityClass(cloudImage, 100, 20, 22, 80, Entity.Layer.BACKGROUND);
    entities.add(cloud);
    entityClass cloud2 = new entityClass(cloudImage, 30, 100, 22, 80, Entity.Layer.BACKGROUND);
    entities.add(cloud2);
    entityClass cloud3 = new entityClass(cloudImage, 420, 50, 22, 80, Entity.Layer.BACKGROUND);
    entities.add(cloud3);

    String cloudImage1 = "/cloud_1.png";
    entityClass cloud1 = new entityClass(cloudImage1, 150, 70, 22, 80, Entity.Layer.BACKGROUND);
    entities.add(cloud1);
    entityClass cloud4 = new entityClass(cloudImage1, 500, 60, 22, 80, Entity.Layer.BACKGROUND);
    entities.add(cloud4);
  }

  /** movement methods are only for confirmation sounds. */
  public boolean jump() {
    return (currentLevel.jump());
  }

  public boolean moveLeft() {
    return (currentLevel.moveLeft());
  }

  public boolean moveRight() {
    return (currentLevel.moveRight());
  }

  public boolean stopMoving() {
    return (currentLevel.stopMoving());
  }

  public void tick() {
    currentLevel.tick();
  }
}
