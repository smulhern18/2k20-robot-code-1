package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class UnspoolClimberCommand extends CommandBase {
  private ClimberSubsystem climberSubsystem;

  public UnspoolClimberCommand(RobotContainer robotContainer) {
    climberSubsystem = robotContainer.climberSubsystem;
    addRequirements(climberSubsystem);
  }

  @Override
  public void execute() {
    if (climberSubsystem.tensiometer.isTaut()) { // unspool
      climberSubsystem.unspool();
    } else { // catch up unspooling
      climberSubsystem.climbOff();
    }
  }

  @Override
  public boolean isFinished() {
    return climberSubsystem.atTop();
  }

  @Override
  public void end(boolean interrupted) {
    climberSubsystem.climbOff();
  }
}
