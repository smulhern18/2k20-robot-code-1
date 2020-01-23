package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;

/**
 * Controls all vision related devices
 */
public class VisionSubsystem extends SubsystemBase {
  private Solenoid lightRing;
  private NetworkTableEntry foundEntry, distanceEntry, angleEntry;

  /**
   * Connects to light ring, NetworkTables
   */
  public VisionSubsystem() {
    lightRing = new Solenoid(VisionConstants.LED_CANNEL);
    NetworkTable table = NetworkTableInstance.getDefault().getTable(VisionConstants.TABLE);
    foundEntry = table.getEntry(VisionConstants.FOUND_ENTRY);
    distanceEntry = table.getEntry(VisionConstants.DISTANCE_ENTRY);
    angleEntry = table.getEntry(VisionConstants.ANGLE_ENTRY);
  }

  /**
   * Sets ring light on or off
   * @param status LED_ON or LED_OFF
   */
  public void setLightRing(boolean status) {
    lightRing.set(status);
  }

  /**
   * Gets whether the target is detected
   * @return state of detection
   */
  public boolean getTargetFound() {
    return foundEntry.getBoolean(false);
  }

  /**
   * Gets the distance to the target
   * @return distance to the target in inches
   */
  public double getDistanceToTarget() {
    return getTargetFound() ? distanceEntry.getDouble(0) : 0;
  }

  /**
   * Gets angle to the target
   * @return angle to the target in (radians?) TODO: check units
   */
  public double getAngleToTarget() {
    return getTargetFound() ? angleEntry.getDouble(0) : 0;
  }
}
