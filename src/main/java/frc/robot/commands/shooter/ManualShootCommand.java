package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.ballpath.RunBallPathCommand;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ManualShootCommand extends SequentialCommandGroup {
  public ManualShootCommand(RobotContainer robotContainer, double value) {
    addCommands(
        new RunShooterCommand(robotContainer, value).withTimeout(4),
        new ParallelCommandGroup(
            new RunShooterCommand(robotContainer, value),
            new RunBallPathCommand(robotContainer, BallPathSubsystem.BallPathDirection.IN)
        ).withTimeout(5)
    );
  }
}
