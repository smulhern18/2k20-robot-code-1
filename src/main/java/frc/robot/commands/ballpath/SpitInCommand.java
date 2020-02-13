package frc.robot.commands.ballpath;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.shooter.SetDefaultRPMCommand;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.subsystems.BallPathSubsystem;

public class SpitInCommand extends SequentialCommandGroup {
  public SpitInCommand(RobotContainer robotContainer) {
    addCommands(

        new SetDefaultRPMCommand(robotContainer),
        new ParallelCommandGroup(
            new RunBallPathCommand(robotContainer, BallPathSubsystem.BallPathDirection.IN),
            new ShootCommand(robotContainer)
        )
    );
  }
}
