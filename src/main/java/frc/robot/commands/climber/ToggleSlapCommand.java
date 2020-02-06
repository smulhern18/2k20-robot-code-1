package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;

public class ToggleSlapCommand extends InstantCommand {
  private ClimberSubsystem climberSubsystem;

  public ToggleSlapCommand(ClimberSubsystem climberSubsystem) {
    this.climberSubsystem = climberSubsystem;
    addRequirements(climberSubsystem);
  }

  @Override
  public void initialize() {
    if (climberSubsystem.getState() == ClimberSubsystem.ClimbState.SLAPPED)
      climberSubsystem.unslap();
    else
      climberSubsystem.slap();
  }
}
