package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.abrahamblinkin.ChangeHatCommand;
import frc.robot.commands.ballpath.RunBallPathCommand;
import frc.robot.commands.trenchable.UntrenchCommand;
import frc.robot.commands.turret.AutoAimTurretCommand;
import frc.robot.subsystems.AbrahamBlinkinSubsystem;
import frc.robot.subsystems.BallPathSubsystem;

public class VisionAimAndShootCommand extends SequentialCommandGroup {
  public VisionAimAndShootCommand(RobotContainer robotContainer) {
    addCommands(
        // untrench
        new UntrenchCommand(robotContainer),
        // aim turret
        new AutoAimTurretCommand(robotContainer),
        // set RPM and empty robot
        new ParallelDeadlineGroup(
            new VisionShootCommand(robotContainer),
            new RunBallPathCommand(robotContainer, BallPathSubsystem.BallPathDirection.IN)
        ),
        // turn off shooter wheel
        new InstantCommand(robotContainer.shooterSubsystem::stop, robotContainer.shooterSubsystem),
        // indicate when done
        new ChangeHatCommand(robotContainer, AbrahamBlinkinSubsystem.Hat.RainbowGlitter, 3)
    );
  }
}
