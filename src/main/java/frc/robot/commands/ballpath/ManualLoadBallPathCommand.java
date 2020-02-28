package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

public class ManualLoadBallPathCommand extends CommandBase {
  private BallPathSubsystem ballPathSubsystem;
  public ManualLoadBallPathCommand(RobotContainer robotContainer) {
    ballPathSubsystem = robotContainer.ballPathSubsystem;
    addRequirements(ballPathSubsystem);
  }

  @Override
  public void execute() {
    ballPathSubsystem.manualLoad();
  }

  @Override
  public void end(boolean interrupted) {
    ballPathSubsystem.stopAll();
  }
}
