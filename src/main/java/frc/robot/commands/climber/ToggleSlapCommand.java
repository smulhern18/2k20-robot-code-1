package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

/**
 * Slaps an unslaps using the same command
 */
public class ToggleSlapCommand extends InstantCommand {
  private ClimberSubsystem climberSubsystem;

  /**
   * Requires climber subsystem
   *
   * @param climberSubsystem the climber subsyste,
   */
  public ToggleSlapCommand(ClimberSubsystem climberSubsystem) {
    this.climberSubsystem = climberSubsystem;
    addRequirements(climberSubsystem);
  }

  /**
   * Flips state of slap
   */
  @Override
  public void initialize() {
    if (climberSubsystem.getState() == ClimberSubsystem.ClimbState.SLAPPED)
      climberSubsystem.unslap();
    else
      climberSubsystem.slap();
  }
}
