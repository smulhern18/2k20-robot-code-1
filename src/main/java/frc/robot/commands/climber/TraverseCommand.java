package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class TraverseCommand extends CommandBase {
  ClimberSubsystem climberSubsystem;
  ClimberSubsystem.TraverseDirection direction;
  public TraverseCommand(RobotContainer robotContainer, ClimberSubsystem.TraverseDirection direction) {
    climberSubsystem = robotContainer.climberSubsystem;
    this.direction = direction;
    addRequirements(climberSubsystem);
  }

  @Override
  public void initialize() {
    climberSubsystem.setTraverseDirection(direction);
  }

  @Override
  public void end(boolean interrupted) {
    climberSubsystem.setTraverseDirection(ClimberSubsystem.TraverseDirection.OFF);
  }
}
