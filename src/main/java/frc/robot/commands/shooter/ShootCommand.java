package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.ballpath.RunBallPathCommand;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends SequentialCommandGroup {
  public ShootCommand(RobotContainer robotContainer, double rpm) {
    addCommands(
        new RampUpShooterCommand(robotContainer, rpm).withTimeout(5),
        new ParallelCommandGroup(
            new RunShooterCommand(robotContainer, rpm),
            new RunBallPathCommand(robotContainer, BallPathSubsystem.BallPathDirection.IN)
        )
    );
  }
}
