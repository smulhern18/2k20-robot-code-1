package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class RetractClimbCommand extends CommandBase {
  private ClimberSubsystem climberSubsystem;

  public RetractClimbCommand(RobotContainer robotContainer) {
    climberSubsystem = robotContainer.climberSubsystem;
    addRequirements(climberSubsystem);
  }

  @Override
  public void execute() {
    climberSubsystem.spool();
  }

  @Override
  public boolean isFinished() {
    return climberSubsystem.atBottom();
  }

  @Override
  public void end(boolean interrupted) {
    climberSubsystem.climbOff();
  }
}
