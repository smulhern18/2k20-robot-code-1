package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.TrenchableConstants;

/**
 * Controls the trenchability of the robot.
 */
public class TrenchableSubsystem extends BeefSubsystemBase {
  public Solenoid trenchablifier;

  /**
   * Constructs trenchabilifier, sets state
   */
  public TrenchableSubsystem() {
    trenchablifier = new Solenoid(TrenchableConstants.TRENCHABLE_PORT);
  }

  /**
   * Trenches robot
   */
  public void trench() {
    trenchablifier.set(true);
  }

  /**
   * Untrenches robot
   */
  public void untrench() {
    trenchablifier.set(false);
  }

}
