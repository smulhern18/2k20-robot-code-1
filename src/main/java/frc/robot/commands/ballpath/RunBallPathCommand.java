package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

public class RunBallPathCommand extends CommandBase {
  BallPathSubsystem ballPathSubsystem;
  BallPathSubsystem.BallPathDirection direction;

  public RunBallPathCommand(RobotContainer robotContainer, BallPathSubsystem.BallPathDirection direction) {
    ballPathSubsystem = robotContainer.ballPathSubsystem;
    this.direction = direction;
    addRequirements(ballPathSubsystem);
  }

  @Override
  public void initialize() {
    switch (direction) {
      case IN:
        ballPathSubsystem.runBelt();
        ballPathSubsystem.kick();
        ballPathSubsystem.indexIn();
        break;
      case OUT:
        ballPathSubsystem.spitBelt();
        ballPathSubsystem.kickOut();
        ballPathSubsystem.indexOut();
        break;
      default:
        System.out.println("UNEXPECTED DIRECTION IN BALLPATH!");
        break;
    }
  }

  @Override
  public void end(boolean interrupted) {
    ballPathSubsystem.stopAll();
  }
}
