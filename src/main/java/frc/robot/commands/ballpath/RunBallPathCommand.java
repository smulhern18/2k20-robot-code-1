package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BallPathSubsystem;

/**
 * Runs the balls path in case of jams or need for manual moving
 */
public class RunBallPathCommand extends CommandBase {
  BallPathSubsystem ballPathSubsystem;
  BallPathSubsystem.BallPathDirection direction;

  /**
   * Creates a new RunBallPathCommand
   * @param robotContainer
   * @param direction
   */
  public RunBallPathCommand(RobotContainer robotContainer, BallPathSubsystem.BallPathDirection direction) {
    ballPathSubsystem = robotContainer.ballPathSubsystem;
    this.direction = direction;
    addRequirements(ballPathSubsystem);
  }

  /**
   * Depending on the direction, the ballspath will spit in or out balls
   */
  @Override
  public void initialize() {
    switch (direction) {
      case IN:
        ballPathSubsystem.runAll();
        break;
      case OUT:
        ballPathSubsystem.spitOutAll();
        break;
      default:
        System.out.println("UNEXPECTED DIRECTION IN BALLPATH!");
        break;
    }
  }

  /**
   * At the end the ballpath stops
   * @param interrupted
   */
  @Override
  public void end(boolean interrupted) {
    ballPathSubsystem.stopAll();
  }
}
