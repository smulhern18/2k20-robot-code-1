package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants.TrenchableConstants;

/**
 * Controls the trenchability of the robot.
 */
public class TrenchableSubsystem extends BeefSubsystemBase {
  private TrenchableState state;
  private DoubleSolenoid trenchablifier;

  /**
   * Constructs trenchabilifier, sets state
   */
  public TrenchableSubsystem() {
    trenchablifier = new DoubleSolenoid(TrenchableConstants.TRENCHABLE_PORT, TrenchableConstants.UNTRENCHABLE_PORT);
    state = trenchablifier.get() == DoubleSolenoid.Value.kForward ? TrenchableState.TRENCHABLE: TrenchableState.UNTRENCHABLE;
  }

  /**
   * Trenches robot
   */
  public void trench() {
    trenchablifier.set(TrenchableConstants.TRENCHABLE);
    state = TrenchableState.TRENCHABLE;
  }

  /**
   * Untrenches robot
   */
  public void untrench() {
    trenchablifier.set(TrenchableConstants.UNTRENCHABLE);
    state = TrenchableState.UNTRENCHABLE;
  }

  /**
   * Gets state of trenchability
   * @return state of subsystem
   */
  public TrenchableState getState() {
    return state;
  }

  /**
   * Enum for the two possible states
   */
  public static enum TrenchableState {
    TRENCHABLE,
    UNTRENCHABLE
  }
}
