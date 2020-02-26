package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

/**
 * Slaps an unslaps using the same command
 */
public class ToggleSlapCommand extends InstantCommand {
  private ClimberSubsystem climberSubsystem;

  /**
   * Requires climber subsystem
   */
  public ToggleSlapCommand(RobotContainer robotContainer) {
    this.climberSubsystem = robotContainer.climberSubsystem;
    addRequirements(climberSubsystem);
  }

  /**
   * Flips state of slap
   */
  @Override
  public void initialize() {
    if (climberSubsystem.slapper.get() == Constants.ClimberConstants.SLAP) {
      climberSubsystem.unslap();
    } else {
      climberSubsystem.slap();
    }
  }
}
