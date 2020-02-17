package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.ManualIntakeCommand;
import frc.robot.commands.shooter.ManualShootCommand;
import frc.robot.subsystems.BallPathSubsystem;

/**
 * Manually de-clog robot (balls move towards shooter)
 */
public class SpitInCommand extends ParallelCommandGroup {
  public SpitInCommand(RobotContainer robotContainer) {
    addCommands(
        // rolls intake wheels
        new ManualIntakeCommand(robotContainer),
        // runs ball path in
        new RunBallPathCommand(robotContainer, BallPathSubsystem.BallPathDirection.IN),
        // flops balls out of shooter
        new ManualShootCommand(robotContainer, Constants.ShooterConstants.DEFAULT_RPM)
    );
  }
}
