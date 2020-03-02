package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.ManualExhaustCommand;
import frc.robot.subsystems.BallPathSubsystem;

/**
 * Manually de-clog robot (balls move towards collector)
 */
public class SpitOutCommand extends ParallelCommandGroup {
  public SpitOutCommand(RobotContainer robotContainer) {
    addCommands(
        // run ball path in reverse
        new RunBallPathCommand(robotContainer, BallPathSubsystem.BallPathDirection.OUT),
        // spit balls from deployed collector
        new ManualExhaustCommand(robotContainer)
    );
  }
}
