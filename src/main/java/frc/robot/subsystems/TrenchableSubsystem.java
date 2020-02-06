package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants.TrenchableConstants;

public class TrenchableSubsystem extends GompeiSubsystemBase {
  private TrenchableState state;
  private DoubleSolenoid trenchablifier;
  public TrenchableSubsystem() {
    trenchablifier = new DoubleSolenoid(TrenchableConstants.TRENCHABLE_PORT, TrenchableConstants.UNTRENCHABLE_PORT);
    state = trenchablifier.get() == DoubleSolenoid.Value.kForward ? TrenchableState.TRENCHABLE: TrenchableState.UNTRENCHABLE;
  }

  public void trench() {
    trenchablifier.set(TrenchableConstants.TRENCHABLE);
    state = TrenchableState.TRENCHABLE;
  }

  public void untrench() {
    trenchablifier.set(TrenchableConstants.UNTRENCHABLE);
    state = TrenchableState.UNTRENCHABLE;
  }

  public TrenchableState getState() {
    return state;
  }

  public static enum TrenchableState {
    TRENCHABLE,
    UNTRENCHABLE
  }
}
