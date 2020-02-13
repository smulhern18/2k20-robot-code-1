package frc.robot.commands.ballpath;

import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

public class SpitOutCommand extends RunBallPathCommand {
  public SpitOutCommand(RobotContainer robotContainer) {
    super(robotContainer, BallPathSubsystem.BallPathDirection.IN);
  }
}
