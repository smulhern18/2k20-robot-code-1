package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.VisionConstants;
import frc.robot.models.GompeiSubsystemBase;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Controls all vision related devices
 */
public class VisionSubsystem extends GompeiSubsystemBase {
  private Solenoid lightRing;
  private NetworkTableEntry dataEntry;
  private boolean found = false;
  private double distance = 0, angle = 0, fps = 0;
  JSONParser parser;

  /**
   * Connects to light ring, NetworkTables
   */
  public VisionSubsystem() {
    lightRing = new Solenoid(VisionConstants.LED_CANNEL);
    NetworkTable table = NetworkTableInstance.getDefault().getTable(VisionConstants.TABLE);
    dataEntry = table.getEntry(VisionConstants.DATA_ENTRY);

    parser = new JSONParser();
    createBooleanEntry(VisionConstants.FOUND_ENTRY, 0, 0, 1, 1, this::getTargetFound);
    createDoubleEntry(VisionConstants.FPS_ENTRY, 0, 1, 1, 1, this::getFPS);
    createDoubleEntry(VisionConstants.DISTANCE_ENTRY, 0, 2, 1, 1, this::getDistanceToTarget);
    createDoubleEntry(VisionConstants.ANGLE_ENTRY, 0, 3, 1, 1, this::getAngleToTarget);
  }

  /**
   * Sets ring light on or off
   *
   * @param status LED_ON or LED_OFF
   */
  public void setLightRing(boolean status) {
    lightRing.set(status);
  }

  /**
   * Gets whether the target is detected
   *
   * @return state of detection
   */
  public boolean getTargetFound() {
    return found;
  }

  /**
   * Gets the distance to the target
   *
   * @return distance to the target in inches
   */
  public double getDistanceToTarget() {
    return getTargetFound() ? distance : 0;
  }

  /**
   * Gets angle to the target
   *
   * @return angle to the target in radians
   */
  public double getAngleToTarget() {
    return getTargetFound() ? angle : 0;
  }

  public double getFPS() {
    return getTargetFound() ? fps : 0;
  }

  private void parseJson() {
    try {
      Object obj = parser.parse(dataEntry.getString("{\"found\": 0, \"distance\": 0, \"angle\": 0, \"fps\": 0}"));
      JSONObject data = (JSONObject) obj;
      found = (long) data.get(VisionConstants.FOUND_KEY) == 1;
      distance = Double.parseDouble(data.get(VisionConstants.DISTANCE_KEY).toString());
      angle = Double.parseDouble(data.get(VisionConstants.ANGLE_KEY).toString());
      fps = Double.parseDouble(data.get(VisionConstants.FPS_KEY).toString());
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update() {
    parseJson();
  }
}
