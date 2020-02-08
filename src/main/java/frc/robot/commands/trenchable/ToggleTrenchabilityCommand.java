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
   *
   * @param trenchableSubsystem the trenchable subsystem
   */
  public ToggleTrenchabilityCommand(TrenchableSubsystem trenchableSubsystem, ClimberSubsystem climberSubsystem) {
    this.trenchableSubsystem = trenchableSubsystem;
    this.climberSubsystem = climberSubsystem;
    addRequirements(trenchableSubsystem);
  }

  /**
   * Swaps state of trenchability
   */
  @Override
  public void initialize() {
    if (trenchableSubsystem.getState() == TrenchableSubsystem.TrenchableState.TRENCHABLE) {
      climberSubsystem.unslap();
      trenchableSubsystem.untrench();
    } else {
      climberSubsystem.slap();
      trenchableSubsystem.trench();
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
