package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants.TrenchableConstants;

/**
 * Controls the trenchability of the robot.
 */
public class TrenchableSubsystem extends BeefSubsystemBase {
  private DoubleSolenoid trenchablifier;
  private DigitalInput untrenchableSwitch;

  /**
   * Constructs trenchabilifier, sets state
   */
  public TrenchableSubsystem() {
    trenchablifier = new DoubleSolenoid(TrenchableConstants.TRENCHABLE_PORT, TrenchableConstants.UNTRENCHABLE_PORT);
    untrenchableSwitch = new DigitalInput(TrenchableConstants.UNTRENCHABLE_SWITCH_PORT);
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
   * Gets state of trenchability
   *
   * @return state of subsystem
   */
  public TrenchableState getState() {
    return untrenchableSwitch.get() ? TrenchableState.UNTRENCHABLE : TrenchableState.TRENCHABLE;
  }

  /**
   * Enum for the two possible states
   */
  public static enum TrenchableState {
    TRENCHABLE,
    UNTRENCHABLE
  }
}
