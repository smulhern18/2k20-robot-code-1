package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.abrahamblinkin.ChangeHatCommand;
import frc.robot.commands.ballpath.RunBallPathCommand;
import frc.robot.subsystems.AbrahamBlinkinSubsystem;
import frc.robot.subsystems.BallPathSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends SequentialCommandGroup {
  public ShootCommand(RobotContainer robotContainer, double rpm) {
    addCommands(
        new RampUpShooterCommand(robotContainer, rpm).withTimeout(4),
        new ParallelCommandGroup(
            new RunShooterCommand(robotContainer, rpm),
            new RunBallPathCommand(robotContainer, BallPathSubsystem.BallPathDirection.IN)
        ).withTimeout(4),
    new InstantCommand(robotContainer.ballPathSubsystem::resetBalls, robotContainer.ballPathSubsystem),
    new StopShootingCommand(robotContainer),
    new ChangeHatCommand(robotContainer, AbrahamBlinkinSubsystem.Hat.RainbowGlitter).withTimeout(3)
    );
  }
}
