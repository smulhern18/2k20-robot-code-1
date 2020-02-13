package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.collector.ManualIntakeCommand;
import frc.robot.commands.shooter.SetDefaultShooterRPMCommand;
import frc.robot.commands.shooter.SpinShooterWheelCommand;
import frc.robot.subsystems.BallPathSubsystem;

/**
 * Manually de-clog robot (balls move towards shooter)
 */
public class SpitInCommand extends SequentialCommandGroup {
  public SpitInCommand(RobotContainer robotContainer) {
    addCommands(
        // sets default rpm of shooter
        new SetDefaultShooterRPMCommand(robotContainer),
        new ParallelCommandGroup(
            // rolls intake wheels
            new ManualIntakeCommand(robotContainer),
            // runs ball path in
            new RunBallPathCommand(robotContainer, BallPathSubsystem.BallPathDirection.IN),
            // flops balls out of shooter
            new SpinShooterWheelCommand(robotContainer)
        )
    );
  }
}
