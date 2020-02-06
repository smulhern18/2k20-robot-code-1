package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TrenchableSubsystem;

/**
 * Toggles trenchability in one command
 */
public class ToggleTrenchabilityCommand extends CommandBase {
  TrenchableSubsystem trenchableSubsystem;

  /**
   * Requires trenchable subsystem
   * @param trenchableSubsystem the trenchable subsystem
   */
  public ToggleTrenchabilityCommand(TrenchableSubsystem trenchableSubsystem) {
    this.trenchableSubsystem = trenchableSubsystem;
    addRequirements(trenchableSubsystem);
  }

  /**
   * Swaps state of trenchability
   */
  @Override
  public void initialize() {
    if (trenchableSubsystem.getState() == TrenchableSubsystem.TrenchableState.TRENCHABLE) {
      trenchableSubsystem.untrench();
    } else {
      trenchableSubsystem.trench();
    }
  }

  /**
   * Instant command
   * @return true
   */
  @Override
  public boolean isFinished() {
    return true;
  }
}
