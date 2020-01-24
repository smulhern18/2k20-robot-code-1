package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;

import org.json.simple.parser.ParseException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Controls all vision related devices
 */
public class VisionSubsystem extends SubsystemBase {
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
   * @return angle to the target in (radians?) TODO: check units
   */
  public double getAngleToTarget() {
    return getTargetFound() ? angle : 0;
  }

  private void parseJson() {
    try {
      Object obj = parser.parse(dataEntry.getString("{\"found\": 0, \"distance\": 0, \"angle\": 0, \"fps\": 0}"));
      JSONObject data = (JSONObject) obj;
      found = (long)data.get(VisionConstants.FOUND) == 1;
      distance = Double.parseDouble(data.get(VisionConstants.DISTANCE).toString());
      angle = Double.parseDouble(data.get(VisionConstants.ANGLE).toString());
      fps = Double.parseDouble(data.get(VisionConstants.FPS).toString());
      SmartDashboard.putBoolean("found", found);
      SmartDashboard.putNumber("distance", distance);
      SmartDashboard.putNumber("angle", angle);
      SmartDashboard.putNumber("fps", fps);

    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void periodic() {
    parseJson();
  }
}
