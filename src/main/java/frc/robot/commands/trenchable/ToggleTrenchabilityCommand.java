package frc.robot.commands.trenchable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.TrenchableSubsystem;

/**
 * Toggles trenchability in one command
 */
public class ToggleTrenchabilityCommand extends CommandBase {
  TrenchableSubsystem trenchableSubsystem;
  ClimberSubsystem climberSubsystem;

  /**
   * Requires trenchable subsystem
   */
  public ToggleTrenchabilityCommand() {
    this.trenchableSubsystem = TrenchableSubsystem.getInstance();
    this.climberSubsystem = ClimberSubsystem.getInstance();
  }

  /**
   * Swaps state of trenchability
   */
  @Override
  public void initialize() {
    if (trenchableSubsystem.getState() == TrenchableSubsystem.TrenchableState.TRENCHABLE) {
      new UntrenchCommand().schedule();
    } else {
      new TrenchCommand().schedule();
    }
  }

  /**
   * Instant command
   *
   * @return true
   */
  @Override
  public boolean isFinished() {
    return true;
  }
}
