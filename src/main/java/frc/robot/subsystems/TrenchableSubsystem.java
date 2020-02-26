package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants.TrenchableConstants;

/**
 * Controls the trenchability of the robot.
 */
public class TrenchableSubsystem extends BeefSubsystemBase {
  public DoubleSolenoid trenchablifier;

  /**
   * Constructs trenchabilifier, sets state
   */
  public TrenchableSubsystem() {
    trenchablifier = new DoubleSolenoid(TrenchableConstants.TRENCHABLE_PORT, TrenchableConstants.UNTRENCHABLE_PORT);
  }

  /**
   * Trenches robot
   */
  public void trench() {
    trenchablifier.set(TrenchableConstants.TRENCHABLE);
  }

  /**
   * Untrenches robot
   */
  public void untrench() {
    trenchablifier.set(TrenchableConstants.UNTRENCHABLE);
  }

  /**
   * Enum for the two possible states
   */
  public enum TrenchableState {
    TRENCHABLE,
    UNTRENCHABLE
  }
}
