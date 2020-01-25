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

import java.text.DecimalFormat;

/**
 * Controls all vision related devices
 */
public class VisionSubsystem extends GompeiSubsystemBase {
  private Solenoid lightRing;
  private NetworkTableEntry dataEntry;
  private boolean found = false;
  private int distance = 0, fps = 0;
  private double angle = 0.0d;
  JSONParser parser;
  private DecimalFormat radianFormatter = new DecimalFormat("0.##### radians");
  private DecimalFormat fpsFormatter = new DecimalFormat("0");

  /**
   * Connects to light ring, NetworkTables
   */
  public VisionSubsystem() {
    lightRing = new Solenoid(VisionConstants.LED_CANNEL);
    NetworkTable table = NetworkTableInstance.getDefault().getTable(VisionConstants.TABLE);
    dataEntry = table.getEntry(VisionConstants.DATA_ENTRY);
    parser = new JSONParser();
    createBooleanEntry(VisionConstants.FOUND_ENTRY, 0, 0, 1, 1, this::getTargetFound);
    createStringEntry(VisionConstants.FPS_ENTRY, 0, 1, 1, 1, () -> fpsFormatter.format(getFPS()));
    createStringEntry(VisionConstants.DISTANCE_ENTRY, 0, 2, 1, 1, () -> getDistanceToTarget() + " in");
    createStringEntry(VisionConstants.ANGLE_ENTRY, 0, 3, 1, 1, () -> radianFormatter.format(getAngleToTarget()));
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
  public int getDistanceToTarget() {
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

  public int getFPS() {
    return getTargetFound() ? fps : 0;
  }

  private void parseJson() {
    try {
      Object obj = parser.parse(dataEntry.getString("{\"found\": 0, \"distance\": 0, \"angle\": 0, \"fps\": 0}"));
      JSONObject data = (JSONObject) obj;
      found = (long) data.get(VisionConstants.FOUND_KEY) == 1;
      distance = Integer.parseInt(data.get(VisionConstants.DISTANCE_KEY).toString());
      angle = Double.parseDouble(data.get(VisionConstants.ANGLE_KEY).toString());
      fps = Integer.parseInt(data.get(VisionConstants.FPS_KEY).toString());
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update() {
    parseJson();
  }
}