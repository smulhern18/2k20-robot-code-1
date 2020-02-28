package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.shooter.ManualShootCommand;
import frc.robot.commands.shooter.RunShooterCommand;

public class ShootThreeAutoCommand extends SequentialCommandGroup {
  public ShootThreeAutoCommand(RobotContainer robotContainer) {
    addCommands(
        new ManualShootCommand(robotContainer).withTimeout(9),
        new InstantCommand(() -> robotContainer.drivetrainSubsystem.drive(-.5, -.5), robotContainer.drivetrainSubsystem).withTimeout(3)
    );
  }
}
