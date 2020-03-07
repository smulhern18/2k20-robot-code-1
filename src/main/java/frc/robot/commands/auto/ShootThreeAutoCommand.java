package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.test.TestTrajectories;
import frc.robot.commands.drivetrain.TimeDriveCommand;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.commands.shooter.ManualShootCommand;
import frc.robot.commands.shooter.RunShooterCommand;
import frc.robot.commands.shooter.ShootCommand;
import frc.robot.commands.turret.VisionAimTurretCommand;

public class ShootThreeAutoCommand extends SequentialCommandGroup {
  public ShootThreeAutoCommand(RobotContainer robotContainer) {
    addCommands(
//        new VisionAimTurretCommand(robotContainer).withTimeout(2),
//        new PrintCommand("auto running"),
        new ShootCommand(robotContainer, 5500),
//        new TrajectoryFollowerCommand(robotContainer, TestTrajectories.BACKWARD)
//        new ManualShootCommand(robotContainer, 6000).withTimeout(9),
        new TimeDriveCommand(robotContainer).withTimeout(.75)
    );
  }
}
