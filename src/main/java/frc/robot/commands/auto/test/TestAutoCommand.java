package frc.robot.commands.auto.test;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TrajectoryFollowerCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * Simple auto that makes a quarter circle, then goes back
 */
public class TestAutoCommand extends SequentialCommandGroup {
  public TestAutoCommand(DrivetrainSubsystem drivetrainSubsystem) {
    addCommands(
        new InstantCommand(drivetrainSubsystem::setBrake, drivetrainSubsystem),
//        new TrajectoryFollowerCommand(
//            TestTrajectories.FORWARD,
//            drivetrainSubsystem),
//        new TrajectoryFollowerCommand(
//            TestTrajectories.BACKWARD,
//            drivetrainSubsystem));
        new TrajectoryFollowerCommand(TestTrajectories.S, drivetrainSubsystem));
  }
}
